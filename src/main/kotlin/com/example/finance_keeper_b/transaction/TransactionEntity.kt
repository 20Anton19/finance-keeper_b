package com.example.finance_keeper_b.transaction

import com.example.finance_keeper_b.category.CategoryEntity
import com.example.finance_keeper_b.user.UserEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "transactions")
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var amount: Double = 0.0,

    @Column(nullable = false) // А можно ли что-то не ввести? я может не помню дату
    var date: LocalDate = LocalDate.now(),

    @Column(nullable = false)
    var type: Boolean = true,



    @ManyToOne
    @JoinColumn(nullable = false)
    var user: UserEntity,

    @ManyToOne
    @JoinColumn(nullable = false)
    var category: CategoryEntity,
)
