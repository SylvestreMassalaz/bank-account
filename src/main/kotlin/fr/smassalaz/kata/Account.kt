package fr.smassalaz.kata

import java.time.LocalDateTime
import java.util.*

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
        val operationDeque = ArrayDeque<Statement>()
        operationRepo.getOperations().forEach {
            operationDeque.push(
                Statement(
                    type = it.type,
                    date = it.date,
                    amount = it.amount,
                    balance = it.computeNextBalance(balance).apply { balance = this }
                )
            )
        }

        printer.printStatements(operationDeque.toList())
    }

    private fun computeCurrentBalance() =
        operationRepo.getOperations().fold(Amount.ZERO) { balance, op -> op.computeNextBalance(balance) }
}