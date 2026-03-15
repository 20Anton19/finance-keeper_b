package com.example.finance_keeper_b.transaction

import com.example.finance_keeper_b.category.CategoryRepo
import com.example.finance_keeper_b.exception.NotFoundException
import com.example.finance_keeper_b.user.UserRepo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TransactionService (
    private val transactionRepo: TransactionRepo,
    private val categoryRepo: CategoryRepo,
    private val userRepo: UserRepo
) {
    fun getAll(): List<TransactionDto> {
        return transactionRepo.findAll().map { it.toDto() }
    }

    fun create(transactionDto: TransactionDto): List<TransactionDto> {
        transactionRepo.save(transactionDto.toEntity(userRepo, categoryRepo))
        return transactionRepo.findAll().map { it.toDto() }
    }

    fun update(transactionDto: TransactionDto): TransactionDto {
        if (transactionRepo.findById(transactionDto.id).orElse(null) != null) {    //сюда бы какое-то сообщение, нашлось ли с таким айди, или нет. И вообще не должно наверное выполняться если такого Id нет
            transactionRepo.save(transactionDto.toEntity(userRepo, categoryRepo))
        }
        return transactionRepo.getReferenceById(transactionDto.id).toDto()
    }
    fun delete(id: Long): List<TransactionDto> {
        if(!transactionRepo.existsById(id)) {
            throw NotFoundException("Транзакцию не нашли")
        }
        transactionRepo.deleteById(id)
        return transactionRepo.findAll().map { it.toDto() }
    }



    fun getFiltered(
        userId: Long,
        type: Boolean?,
        categoryId: Long?,
        dateFrom: LocalDate?,
        dateTo: LocalDate?,
        amountFrom: Double?,
        amountTo: Double?,
        pageable: Pageable
    ): Page<TransactionDto> {
        val spec = TransactionSpec.forUser(userId)
            .and(TransactionSpec.byType(type))
            .and(TransactionSpec.byCategory(categoryId))
            .and(TransactionSpec.byDateFrom(dateFrom))
            .and(TransactionSpec.byDateTo(dateTo))
            .and(TransactionSpec.byAmountFrom(amountFrom))
            .and(TransactionSpec.byAmountTo(amountTo))

        return transactionRepo.findAll(spec, pageable).map { it.toDto() }
    }
}