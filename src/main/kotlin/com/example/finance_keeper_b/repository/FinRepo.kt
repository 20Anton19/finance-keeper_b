package com.example.finance_keeper_b.repository

import com.example.finance_keeper_b.TransactionDto
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
class FinRepo {
    private val transaction = TransactionDto(
        id = 1000,
        userId = 123,
        categoryId = 2,
        amount = 3500.0,
        date = LocalDate.now(),
        type = true
    )

    private val transactionList = mutableListOf<TransactionDto>(transaction)
    fun takeTransactionList() = transactionList
    fun insertTransaction(medicine: TransactionDto): List<TransactionDto> {
        transactionList.add(medicine)
        return transactionList
    }

    fun deleteTransaction(id: Long): List<TransactionDto> {
        transactionList.removeIf { it.id == id }
        return transactionList
    }
}