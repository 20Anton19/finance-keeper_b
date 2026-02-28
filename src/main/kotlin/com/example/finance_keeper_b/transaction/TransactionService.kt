package com.example.finance_keeper_b.transaction

import com.example.finance_keeper_b.category.CategoryRepo
import com.example.finance_keeper_b.user.UserRepo
import org.springframework.stereotype.Service

@Service
class TransactionService (
    private val transactionRepo: TransactionRepo,
    private val categoryRepo: CategoryRepo,
    private val userRepo: UserRepo
) {
        fun getAll(): List<TransactionDto> = transactionRepo.findAll().map { it.toDto() }

        fun create(transactionDto: TransactionDto): List<TransactionDto> {
            transactionRepo.save(transactionDto.toEntity(userRepo, categoryRepo))
            return transactionRepo.findAll().map { it.toDto() }
        }
        fun delete(id: Long): List<TransactionDto> {
            transactionRepo.deleteById(id)
            return transactionRepo.findAll().map { it.toDto() }
        }

        fun update(transactionDto: TransactionDto): TransactionDto {
            if (transactionRepo.findById(transactionDto.id).orElse(null) != null) {    //сюда бы какое-то сообщение, нашлось ли с таким айди, или нет. И вообще не должно наверное выполняться если такого Id нет
                transactionRepo.save(transactionDto.toEntity(userRepo, categoryRepo))
            }
            return transactionRepo.getReferenceById(transactionDto.id).toDto()
        }
    }