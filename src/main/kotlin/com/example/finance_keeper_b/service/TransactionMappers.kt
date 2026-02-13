package com.example.finance_keeper_b.service

import com.example.finance_keeper_b.TransactionDto
import com.example.finance_keeper_b.repository.TransactionEntity

fun TransactionEntity.toDto(): TransactionDto {
    return TransactionDto(
        id = this.id,
        userId = this.userId,
        categoryId = this.categoryId,
        amount = this.amount,
        date = this.date,
        type = this.type
    )
}

fun TransactionDto.toEntity(): TransactionEntity {
    return TransactionEntity(
        id = this.id,
        userId = this.userId,
        categoryId = this.categoryId,
        amount = this.amount,
        date = this.date,
        type = this.type
    )
}