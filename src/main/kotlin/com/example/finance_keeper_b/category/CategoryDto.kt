package com.example.finance_keeper_b.category

import com.example.finance_keeper_b.transaction.TransactionEntity

data class CategoryDto(
    val id: Long,
    var color: Int,
    var name: String,
    var userId: Long
)
