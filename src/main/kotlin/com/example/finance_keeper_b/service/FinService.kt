package com.example.finance_keeper_b.service

import com.example.finance_keeper_b.TransactionDto
import com.example.finance_keeper_b.repository.FinRepo
import com.example.finance_keeper_b.repository.TransactionEntity
import org.springframework.stereotype.Service

@Service
class FinService (
    private val repo: FinRepo
) {
        fun getAll(): List<TransactionDto> = repo.findAll().map { it.toDto() }

        fun create(transactionEntity: TransactionEntity): List<TransactionDto> {
            repo.save(transactionEntity)
            return repo.findAll().map { it.toDto() }
        }
        fun delete(id: Long): List<TransactionDto> {
            repo.deleteById(id)
            return repo.findAll().map { it.toDto() }
        }

        fun update(id: Long, transactionEntity: TransactionEntity): TransactionDto {
            val existing = repo.findById(id).orElseThrow()
            val updated = existing.copy(
                userId = transactionEntity.userId,
                categoryId = transactionEntity.categoryId,
                amount = transactionEntity.amount,
                date = transactionEntity.date,
                type = transactionEntity.type
            )
            return repo.save(updated).toDto()
        }
    }