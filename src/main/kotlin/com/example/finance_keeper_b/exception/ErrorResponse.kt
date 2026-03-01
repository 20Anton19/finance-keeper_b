package com.example.finance_keeper_b.exception

import java.time.Instant
import javax.security.auth.kerberos.KerberosCredMessage

data class ErrorResponse(
    val message: String,
    val details: Map<String, String?>? = null,
    val timestamp: Instant
)
