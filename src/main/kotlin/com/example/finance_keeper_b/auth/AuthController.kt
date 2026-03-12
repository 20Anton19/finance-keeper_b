package com.example.finance_keeper_b.auth

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
// Все эндпоинты этого контроллера начинаются с /auth
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(@RequestBody request: AuthRequestDto): AuthResponseDto {
        return authService.register(request)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequestDto): AuthResponseDto {
        return authService.login(request)
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody request: RefreshRequestDto): AuthResponseDto {
        return authService.refresh(request)
    }

    @PostMapping("/logout")
    fun logout(@RequestBody request: RefreshRequestDto): ResponseEntity<Unit> {
        authService.logout(request)
        return ResponseEntity.ok().build()
    }
}