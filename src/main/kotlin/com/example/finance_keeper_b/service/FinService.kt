package com.example.finance_keeper_b.service

import com.example.finance_keeper_b.TransactionDto
import com.example.finance_keeper_b.repository.FinRepo
import org.springframework.stereotype.Service

@Service
class FinService (
    private val repository: FinRepo
) {
        fun takeTransactionList() = repository.takeTransactionList()
        fun insertTransaction(transaction: TransactionDto): List<TransactionDto>  {
            return repository.insertTransaction(transaction)
        }

        fun deleteTransaction(id: Long): List<TransactionDto>  {
            return repository.deleteTransaction(id)
        }
    }