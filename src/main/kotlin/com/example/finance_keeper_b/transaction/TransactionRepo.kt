package com.example.finance_keeper_b.transaction

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface TransactionRepo: JpaRepository<TransactionEntity, Long> , JpaSpecificationExecutor<TransactionEntity> {
    // Найти всех по Id категории
    fun findAllByCategoryId(categoryId: Long) : List<TransactionEntity>

    @Query("SELECT SUM(t.amount) FROM TransactionEntity t WHERE t.user.id = :userId AND t.type = true")
    fun getIncomeSumByUserId(userId: Long): Int

    @Query("SELECT SUM(t.amount) FROM TransactionEntity t WHERE t.user.id = :userId AND t.type = false")
    fun getExpenseSumByUserId(userId: Long): Int
}