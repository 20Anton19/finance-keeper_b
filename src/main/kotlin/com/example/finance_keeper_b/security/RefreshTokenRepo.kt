package com.example.finance_keeper_b.security

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RefreshTokenRepo: JpaRepository<RefreshTokenEntity, Long> {
    fun findByToken(token: String): RefreshTokenEntity?

//    fun findAllByUserId(userId: Long): List<RefreshTokenEntity>
    @Query("SELECT r FROM RefreshTokenEntity r WHERE r.user.id = :userId")
    fun findAllByUserId(@Param("userId") userId: Long): List<RefreshTokenEntity>
}