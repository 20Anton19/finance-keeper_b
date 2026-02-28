package com.example.finance_keeper_b.category

import com.example.finance_keeper_b.transaction.TransactionEntity
import com.example.finance_keeper_b.user.UserEntity
import jakarta.persistence.*


@Entity
@Table(name = "categories")
data class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column // Либо делать nullable, либо ставить цвет по умолчанию
    var color: Int = 0,

    @Column
    var name: String = "BaseName",


    @ManyToOne
    @JoinColumn(nullable = false)
    var user: UserEntity = UserEntity(),

    @OneToMany(mappedBy = "category")
    var transactions: List<TransactionEntity> = emptyList()
)
