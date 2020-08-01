package fr.smassalaz.kata

import java.math.BigDecimal.ZERO
import java.time.LocalDateTime

class Account(private val operationRepo: OperationRepository) {

    fun deposit(amount: Amount, date: LocalDateTime) {
        checkForNegativeAmount(amount)
        operationRepo.addOperation(
            Operation(OperationType.DEPOSIT, date, amount)
        )
    }

    fun withdraw(amount: Amount, date: LocalDateTime) {
        checkForNegativeAmount(amount)
        if (computeCurrentBalance() < amount) {
            throw InsufficientFundsException()
        }
        operationRepo.addOperation(
            Operation(OperationType.WITHDRAWAL, date, amount)
        )
    }

    private fun checkForNegativeAmount(amount: Amount) {
        if (amount.isNegative()) {
            throw NegativeAMountException()
        }
    }

    private fun computeCurrentBalance() =
        operationRepo.getOperations().fold(Amount(ZERO)) { balance, op -> op.computeBalance(balance) }
}