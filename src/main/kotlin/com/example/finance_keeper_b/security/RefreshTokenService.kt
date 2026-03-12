package com.example.finance_keeper_b.security

import com.example.finance_keeper_b.user.UserEntity
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class RefreshTokenService(
    private val refreshTokenRepo: RefreshTokenRepo,
    @Value("\${JWT_REFRESH_EXPIRATION}") private var refreshExpiration: Long = 0
) {
    fun createRefreshToken(user: UserEntity): RefreshTokenEntity {
        val token = RefreshTokenEntity(
            token = UUID.randomUUID().toString(),
            expiresAt = Instant.now().plusMillis(refreshExpiration),
            revoked = false, 
            user = user
        )
        return refreshTokenRepo.save(token)
    }

    fun validateRefreshToken(token: String): RefreshTokenEntity? {
        val refreshToken = refreshTokenRepo.findByToken(token) ?: return null

        if (refreshToken.revoked || refreshToken.expiresAt.isBefore(Instant.now())) {
            return null
        }

        return refreshToken
    }
//    fun revokeAllUserTokens(user: UserEntity) {
//        val tokens = refreshTokenRepo.findAllByUserId(user.id)
//        tokens.forEach { it.revoked = true }
//        refreshTokenRepo.saveAll(tokens)
//    }
    fun revokeAllUserTokens(user: UserEntity) {
        println(">>> revokeAll: ищем токены для userId=${user.id}")
        try {
            val tokens = refreshTokenRepo.findAllByUserId(user.id)
            println(">>> revokeAll: найдено токенов=${tokens.size}")
            tokens.forEach { it.revoked = true }
            refreshTokenRepo.saveAll(tokens)
            println(">>> revokeAll: готово")
        } catch (e: Exception) {
            println(">>> revokeAll ОШИБКА: ${e::class.simpleName}")
            println(">>> revokeAll СООБЩЕНИЕ: ${e.message}")
            println(">>> revokeAll ПРИЧИНА: ${e.cause?.message}")
            throw e
        }
    }


}