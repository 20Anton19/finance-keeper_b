package com.example.finance_keeper_b.category

import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepo: JpaRepository<CategoryEntity, Long> {

}