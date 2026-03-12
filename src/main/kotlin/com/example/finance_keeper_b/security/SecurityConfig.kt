package com.example.finance_keeper_b.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.security.Security

//@Configuration
//class SecurityConfig {
//    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
//        return httpSecurity
//            .csrf { csrf -> csrf.disable()}
//            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
//            .build()
//    }
//
//}

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtFilter: JwtAuthFilter,
    private val userDetailsService: UserDetailsService
) {

    // @Bean — этот метод возвращает объект которым Spring будет управлять.
    // SecurityFilterChain определяет правила безопасности для HTTP запросов.
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            // Отключаем CSRF защиту.
            // CSRF актуален для браузерных сессий с cookies.
            // Мы используем JWT в заголовках — CSRF нам не страшен.
            .csrf { it.disable() }

            .authorizeHttpRequests {
                // /auth/** — публичные эндпоинты, токен не нужен.
                // ** означает любой путь после /auth/
                it.requestMatchers("/auth/**").permitAll()
                // Все остальные запросы требуют аутентификации
                it.anyRequest().authenticated()
            }

            .sessionManagement {
                // STATELESS — Spring не создаёт HTTP сессии.
                // Мы stateless: каждый запрос самодостаточен благодаря JWT.
                // Альтернатива — ALWAYS (сессии), но это противоречит идее JWT.
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }

            // Говорим Spring какой провайдер аутентификации использовать
            .authenticationProvider(authenticationProvider())

            // Добавляем наш JWT фильтр ПЕРЕД стандартным фильтром логина.
            // Порядок важен: сначала проверяем JWT, потом уже стандартная логика.
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    // AuthenticationProvider — связывает UserDetailsService и PasswordEncoder.
    // DaoAuthenticationProvider — реализация для работы с БД (Dao = Data Access Object).
    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        // Говорим как загружать пользователя
        provider.setUserDetailsService(userDetailsService)
        // Говорим как проверять пароль
        provider.setPasswordEncoder(passwordEncoder())
        return provider
    }

    // BCrypt — стандарт для хэширования паролей.
    // Автоматически добавляет соль, устойчив к rainbow table атакам.
    // Альтернатива — SHA-256, MD5, но они устарели и небезопасны для паролей.
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    // AuthenticationManager используется в AuthService для проверки логина/пароля.
    // Берём его из конфигурации Spring — не создаём вручную.
    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager
}
