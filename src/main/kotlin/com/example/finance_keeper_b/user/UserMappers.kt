package com.example.finance_keeper_b.user

import com.example.finance_keeper_b.category.CategoryEntity
import com.example.finance_keeper_b.transaction.TransactionEntity
import java.time.Instant

fun UserEntity.toDto(): UserDto {
    return UserDto(
        id = this.id,
        login = this.login,
        password = this.password,
        createdAt = this.createdAt, // надо передавать, а то настоящее время запишется
    )
}

fun UserDto.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        login = this.login,
        password = this.password,
        createdAt = this.createdAt, // надо передавать, а то настоящее время запишется,
        categories = emptyList(),
        transactions = emptyList()
    )
}