package com.example.finance_keeper_b.user

import com.example.finance_keeper_b.category.CategoryEntity
import com.example.finance_keeper_b.transaction.TransactionEntity
import java.time.Instant

data class UserDto(
    val id: Long,
    var login: String,
    var password: String,
    var createdAt: Instant = Instant.now()
)
