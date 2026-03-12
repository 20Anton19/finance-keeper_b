package com.example.finance_keeper_b.auth

import com.example.finance_keeper_b.exception.BadRequestException
import com.example.finance_keeper_b.exception.NotFoundException
import com.example.finance_keeper_b.security.JwtService
import com.example.finance_keeper_b.security.RefreshTokenService
import com.example.finance_keeper_b.user.UserEntity
import com.example.finance_keeper_b.user.UserRepo
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class AuthService(
    private val userRepo: UserRepo,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val refreshTokenService: RefreshTokenService,
    private val authenticationManager: AuthenticationManager
) {
    fun register(request: AuthRequestDto): AuthResponseDto {
        if (userRepo.findByLogin(request.login) != null) {
            throw BadRequestException("Логин уже занят")
        }
        val user = UserEntity(
            id = 0,
            login = request.login,
            password = passwordEncoder.encode(request.password),
            createdAt = Instant.now(),
            categories = emptyList(),
            transactions = emptyList()
        )

        val savedUser = userRepo.save(user)

        return buildAuthResponse(savedUser)

    }

//    fun login(request: AuthRequestDto): AuthResponseDto {
//        authenticationManager.authenticate(
//            UsernamePasswordAuthenticationToken(request.login, request.password)
//        )
//        val user = userRepo.findByLogin(request.login) ?: throw NotFoundException("Пользователь не найден")
//        refreshTokenService.revokeAllUserTokens(user)
//        return buildAuthResponse(user)
//    }
        fun login(request: AuthRequestDto): AuthResponseDto {
            println(">>> шаг 1: authenticate")
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.login, request.password))

            println(">>> шаг 2: findByLogin")
            val user = userRepo.findByLogin(request.login) ?: throw NotFoundException("Пользователь не найден")

            println(">>> шаг 3: revokeAll")
            refreshTokenService.revokeAllUserTokens(user)

            println(">>> шаг 4: buildResponse")
            return buildAuthResponse(user)
        }

    fun refresh(request: RefreshRequestDto): AuthResponseDto {
        val refreshToken = refreshTokenService.validateRefreshToken((request.refreshToken))
            ?: throw RuntimeException("Refresh токен невалиден или истёк")

        val user = refreshToken.user

        refreshTokenService.revokeAllUserTokens(user)

        return buildAuthResponse(user)
    }

    fun logout(request: RefreshRequestDto) {
        val refreshToken = refreshTokenService.validateRefreshToken(request.refreshToken)
            ?: return
        refreshTokenService.revokeAllUserTokens(refreshToken.user)
    }

    private fun buildAuthResponse(user: UserEntity): AuthResponseDto {
        val accessToken = jwtService.generateAccessToken(user)
        val refreshToken = refreshTokenService.createRefreshToken(user)
        return AuthResponseDto(
            accessToken = accessToken,
            refreshToken = refreshToken.token
        )
    }
}