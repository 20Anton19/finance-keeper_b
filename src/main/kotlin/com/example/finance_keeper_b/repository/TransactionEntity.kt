package com.example.finance_keeper_b.repository

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "transactions")
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val userId: Long = 0,
    val categoryId: Long = 0,
    val amount: Double = 0.0,
    val date: LocalDate = LocalDate.now(),
    val type: Boolean = true
)
