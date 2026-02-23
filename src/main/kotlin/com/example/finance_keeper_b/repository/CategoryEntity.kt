package com.example.finance_keeper_b.repository

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table


@Entity
@Table(name = "categories")
data class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column // Либо делать nullable, либо ставить цвет по умолчанию
    var color: Int,



    @OneToMany(mappedBy = "categoryId")
    var transactions: List<TransactionEntity>
)
