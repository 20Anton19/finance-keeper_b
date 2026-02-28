package com.example.finance_keeper_b.category

import com.example.finance_keeper_b.transaction.TransactionRepo
import com.example.finance_keeper_b.user.UserRepo

fun CategoryEntity.toDto(): CategoryDto {
    return CategoryDto(
        id = this.id,
        color = this.color,
        name = this.name,
        userId = this.user.id
    )
}


fun CategoryDto.toEntity(
    userRepo: UserRepo
): CategoryEntity {
    return CategoryEntity (
        id = this.id,
        color = this.color,
        name = this.name,
        user = userRepo.getReferenceById(this.userId), // а юзер у нас разве не наллабл должен быть
        transactions = emptyList() // Не грузим транзакции при создании, пока пустышка
    )
}