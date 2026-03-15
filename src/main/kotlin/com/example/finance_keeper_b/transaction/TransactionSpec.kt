package com.example.finance_keeper_b.transaction

import org.springframework.data.jpa.domain.Specification
import java.time.LocalDate

object TransactionSpec {
    fun forUser(userId: Long): Specification<TransactionEntity> =
        Specification { root, _, cb -> cb.equal(root.get<Long>("user").get<Long>("id"), userId) }

    fun byType(type: Boolean?): Specification<TransactionEntity>? =
        type?.let { Specification { root, _, cb -> cb.equal(root.get<Boolean>("type"), it) } }

    fun byCategory(categoryId: Long?): Specification<TransactionEntity>? =
        categoryId?.let { Specification { root, _, cb -> cb.equal(root.get<Any>("category").get<Long>("id"), it) } }

    fun byDateFrom(from: LocalDate?): Specification<TransactionEntity>? =
        from?.let { Specification { root, _, cb -> cb.greaterThanOrEqualTo(root.get("date"), it) } }

    fun byDateTo(to: LocalDate?): Specification<TransactionEntity>? =
        to?.let { Specification { root, _, cb -> cb.lessThanOrEqualTo(root.get("date"), it) } }

    fun byAmountFrom(min: Double?): Specification<TransactionEntity>? =
        min?.let { Specification { root, _, cb -> cb.greaterThanOrEqualTo(root.get("amount"), it) } }

    fun byAmountTo(max: Double?): Specification<TransactionEntity>? =
        max?.let { Specification { root, _, cb -> cb.lessThanOrEqualTo(root.get("amount"), it) } }
}