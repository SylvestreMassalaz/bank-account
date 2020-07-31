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
        val balance = computeCurrentBalance()
        if(balance.value < amount.value) {
            throw InsufficientFundsException()
        }
        operationRepo.addOperation(
            Operation(OperationType.WITHDRAWAL, date, amount)
        )
    }

    private fun computeCurrentBalance(): Amount {
        var balance = Amount(BigDecimal.ZERO)
        operationRepo.getOperations().forEach { balance = it.computeBalance(balance) }
        return balance
    }
}