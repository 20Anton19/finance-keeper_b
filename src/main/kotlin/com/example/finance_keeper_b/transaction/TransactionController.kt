package com.example.finance_keeper_b.transaction

import com.example.finance_keeper_b.user.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/transactions")
class TransactionController(
    private val service: TransactionService
) {
    @GetMapping()
    fun getAllTransactions(): List<TransactionDto> {
        return service.getAll()
    }

    @PostMapping()
    fun postTransaction(@RequestBody transactionDto: TransactionDto): List<TransactionDto> {
        return service.create(transactionDto)
    }

    @PutMapping() // приходит только обьект, без отдельного айди. Может быть нужно отдельно?
    fun putTransaction(@RequestBody transactionDto: TransactionDto): TransactionDto {
        return service.update(transactionDto)
    }

    @DeleteMapping("/{id}")
    fun deleteTransaction(@PathVariable id: Long): List<TransactionDto> {
        return service.delete(id)
    }

    @GetMapping("/filtered")
    fun getFiltered(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(required = false) type: Boolean?,
        @RequestParam(required = false) categoryId: Long?,
        @RequestParam(required = false) dateFrom: LocalDate?,
        @RequestParam(required = false) dateTo: LocalDate?,
        @RequestParam(required = false) amountFrom: Double?,
        @RequestParam(required = false) amountTo: Double?
    ): Page<TransactionDto> {
        val user = SecurityContextHolder.getContext().authentication.principal as UserEntity
        val pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "date"))
        return service.getFiltered(user.id, type, categoryId, dateFrom, dateTo, amountFrom, amountTo, pageable)
    }
}




















//    val myList = mutableListOf(1,2,3)
//    @GetMapping
//    fun getTest(): TestResponse {
//        return TestResponse(
//            message = "Бэкенд работает!",
//            time = LocalDateTime.now().toString()
//        )
//    }
//    @PostMapping
//    fun postTest(@RequestBody num: numDto): List<Int> {
//        myList.add(num.num)
//        return myList
//    }