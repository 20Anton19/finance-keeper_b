package com.example.finance_keeper_b.repository

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column
    var login: String,

    @Column
    var password: String,

    @CreationTimestamp
    var createdAt: Instant = Instant.now(),



    @OneToMany(mappedBy = "userId")
    var categories: List<CategoryEntity>,

    @OneToMany(mappedBy = "userId")
    var transactions: List<TransactionEntity>
)
