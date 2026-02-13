package com.example.finance_keeper_b.repository

import org.springframework.data.jpa.repository.JpaRepository

interface FinRepo: JpaRepository<TransactionEntity, Long>