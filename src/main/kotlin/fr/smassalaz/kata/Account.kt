package fr.smassalaz.kata

import java.math.BigDecimal.ZERO
import java.time.LocalDateTime

class Account(private val operationRepo: OperationRepository, private val printer: StatementPrinter) {

    fun deposit(amount: Amount, date: LocalDateTime) {
        checkForNegativeAmount(amount)
        operationRepo.addOperation(
            Operation.deposit(date, amount)
        )
    }

    fun withdraw(amount: Amount, date: LocalDateTime) {
        checkForNegativeAmount(amount)
        if (computeCurrentBalance() < amount) {
            throw InsufficientFundsException()
        }
        operationRepo.addOperation(
            Operation.withdrawal(date, amount)
        )
    }

    private fun checkForNegativeAmount(amount: Amount) {
        if (amount.isNegative()) {
            throw NegativeAMountException()
        }
    }

    private fun computeCurrentBalance() =
        operationRepo.getOperations().fold(Amount(ZERO)) { balance, op -> op.computeBalance(balance) }

    fun printStatements() {
        var balance = Amount(ZERO)
        val statements = operationRepo.getOperations().map {
            Statement(
                type = it.type,
                date = it.date,
                amount = it.amount,
                balance = it.computeBalance(balance).apply { balance = this }
            )
        }
        printer.printStatements(statements)
    }
}