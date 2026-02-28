package com.example.finance_keeper_b.user

import com.example.finance_keeper_b.category.CategoryEntity
import com.example.finance_keeper_b.transaction.TransactionEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    var login: String = "baseLogin",

    @Column
    var password: String = "basePassword",

    @CreationTimestamp
    var createdAt: Instant = Instant.now(),



    @OneToMany(mappedBy = "user")
    var categories: List<CategoryEntity> = emptyList(),

    @OneToMany(mappedBy = "user")
    var transactions: List<TransactionEntity> = emptyList()
)
