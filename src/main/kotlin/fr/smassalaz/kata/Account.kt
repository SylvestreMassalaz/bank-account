package fr.smassalaz.kata

import java.math.BigDecimal
import java.time.LocalDateTime

class Account (private val operationRepo: OperationRepository) {
    fun deposit(amount: Amount, date: LocalDateTime) {
        if(amount.value < BigDecimal.ZERO) {
            throw NegativeAMountException()
        }
        operationRepo.addOperation(
            Operation(OperationType.DEPOSIT, date, amount)
        )
    }

    fun withdraw(amount: Amount, date: LocalDateTime) {
        operationRepo.addOperation(
            Operation(OperationType.WITHDRAWAL, date, amount)
        )
    }
}