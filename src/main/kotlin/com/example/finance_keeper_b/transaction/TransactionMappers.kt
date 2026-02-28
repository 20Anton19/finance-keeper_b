package com.example.finance_keeper_b.transaction

import com.example.finance_keeper_b.category.CategoryRepo
import com.example.finance_keeper_b.user.UserRepo

fun TransactionEntity.toDto(): TransactionDto {
    return TransactionDto(
        id = this.id,
        userId = this.id,
        categoryId = this.id,
        amount = this.amount,
        date = this.date,
        type = this.type
    )
}

fun TransactionDto.toEntity(
    userRepo: UserRepo,
    categoryRepo: CategoryRepo
): TransactionEntity {
    return TransactionEntity(
        id = this.id,
        user = userRepo.getReferenceById(this.userId),
        category = categoryRepo.getReferenceById(this.categoryId),
        amount = this.amount,
        date = this.date,
        type = this.type
    )
}

