package com.example.finance_keeper_b

import com.example.finance_keeper_b.service.FinService
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping()
class FinController(
    private val service: FinService
) {
    @GetMapping()
    fun takeTransactionList(): List<TransactionDto> {
        return service.takeTransactionList()
    }

    @PostMapping()
    fun postTransaction(@RequestBody transaction: TransactionDto): List<TransactionDto> {
        return service.insertTransaction(transaction)
    }

    @DeleteMapping("/{id}")
    fun deleteTransaction(@PathVariable id: Long): List<TransactionDto> {
        return service.deleteTransaction(id)
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