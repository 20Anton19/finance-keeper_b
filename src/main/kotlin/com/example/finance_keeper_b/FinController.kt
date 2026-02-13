package com.example.finance_keeper_b

import com.example.finance_keeper_b.repository.TransactionEntity
import com.example.finance_keeper_b.service.FinService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/transactions")
class FinController(
    private val service: FinService
) {
    @GetMapping()
    fun takeTransactionList(): List<TransactionDto> {
        return service.getAll()
    }

    @PostMapping()
    fun postTransaction(@RequestBody transactionEntity: TransactionEntity): List<TransactionDto> {
        return service.create(transactionEntity)
    }

//    @PutMapping()
//    fun putTransaction(@RequestBody transaction: Transaction): TransactionDto {
//        return service.update(transaction.id)
//    }

    @DeleteMapping("/{id}")
    fun deleteTransaction(@PathVariable id: Long): List<TransactionDto> {
        return service.delete(id)
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