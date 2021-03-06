package fr.smassalaz.kata.business

import java.time.LocalDateTime

class Account(private val operationRepo: OperationRepository) {

    fun deposit(amount: Amount, date: LocalDateTime) {
        operationRepo.addOperation(
            Operation.deposit(date, amount)
        )
    }

    fun withdraw(amount: Amount, date: LocalDateTime) {
        if (computeCurrentBalance() < amount) {
            throw InsufficientFundsException()
        }
        operationRepo.addOperation(
            Operation.withdrawal(date, amount)
        )
    }

    fun printStatements(printer: StatementPrinter) {
        var balance = Amount.ZERO
        val statements = operationRepo.getOperations()
            .map {
                Statement(
                    type = it.type,
                    date = it.date,
                    amount = it.amount,
                    balance = it.computeNextBalance(balance).apply { balance = this }
                )
            }
            .asReversed()

        printer.printStatements(statements)
    }

    private fun computeCurrentBalance() =
        operationRepo.getOperations().fold(Amount.ZERO) { balance, op -> op.computeNextBalance(balance) }
}