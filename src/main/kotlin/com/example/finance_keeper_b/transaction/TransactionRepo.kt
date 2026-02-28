package com.example.finance_keeper_b.transaction

import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepo: JpaRepository<TransactionEntity, Long> {
    // Найти всех по Id категории
    fun findAllByCategoryId(categoryId: Long) : List<TransactionEntity>
}