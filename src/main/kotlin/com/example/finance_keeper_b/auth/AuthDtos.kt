package com.example.finance_keeper_b.auth

data class AuthRequestDto(
    val login: String,
    val password: String
)

data class AuthResponseDto(
    val accessToken: String,
    val refreshToken: String
)

data class RefreshRequestDto(
    val refreshToken: String
)