package com.example.finance_keeper_b

import java.time.LocalDate
import java.util.*

data class TransactionDto(
    val id: Long,
    val userId: Long,
    val categoryId: Long,
    val amount: Double,
    val date: LocalDate,
    val type: Boolean
)


