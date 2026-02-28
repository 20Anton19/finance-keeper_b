package com.example.finance_keeper_b.transaction

import org.springframework.web.bind.annotation.*

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