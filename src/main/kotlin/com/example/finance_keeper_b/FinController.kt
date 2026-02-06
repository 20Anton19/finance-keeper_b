package com.example.finance_keeper_b

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/test")
class FinController {

    @GetMapping
    fun test(): TestResponse {
        return TestResponse(
            message = "Бэкенд работает!",
            time = LocalDateTime.now().toString()
        )
    }
}