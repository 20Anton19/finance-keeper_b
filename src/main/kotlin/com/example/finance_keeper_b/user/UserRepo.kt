package com.example.finance_keeper_b.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo: JpaRepository<UserEntity, Long> {
}