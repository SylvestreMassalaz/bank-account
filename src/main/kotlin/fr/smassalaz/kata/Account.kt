package fr.smassalaz.kata

import java.time.LocalDateTime

class Account (private val operationRepo: OperationRepository) {
    fun deposit(amount: Amount, date: LocalDateTime) {
        operationRepo.addOperation(
            Operation(OperationType.DEPOSIT, date, amount)
        )
    }
}