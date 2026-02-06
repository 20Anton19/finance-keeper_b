package com.example.finance_keeper_b

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FinController {
    @GetMapping
    fun helloWorld() = "Hello world!"
}