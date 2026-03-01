package com.example.finance_keeper_b.exception

import java.lang.RuntimeException

class BadRequestException(message: String): RuntimeException(message) {
}