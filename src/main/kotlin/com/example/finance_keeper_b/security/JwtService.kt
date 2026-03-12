package com.example.finance_keeper_b.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService(
    @Value("\${JWT_SECRET_BASE64}") private val jwtSecret: String,
    @Value("\${JWT_ACCESS_EXPIRATION}") private var accessExpiration: Long
) {
    private val secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret)) // Мб lateinit?


    fun generateAccessToken(user: UserDetails): String {
        return Jwts.builder()
            .subject(user.username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis()+ accessExpiration))
            .signWith(secretKey, Jwts.SIG.HS256)
            .compact()
    }

    fun extractUsername(token: String): String {
        return extractClaims(token).subject
    }

    fun isTokenValid(token: String, user: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == user.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractClaims(token).expiration.before(Date())
    }
    private fun extractClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }
}