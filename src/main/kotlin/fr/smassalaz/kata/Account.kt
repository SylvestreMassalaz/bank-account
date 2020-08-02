package fr.smassalaz.kata

import java.time.LocalDateTime

class Account(private val operationRepo: OperationRepository, private val printer: StatementPrinter) {

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

    fun printStatements() {
        var balance = Amount.ZERO
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

    private fun computeCurrentBalance() =
        operationRepo.getOperations().fold(Amount.ZERO) { balance, op -> op.computeBalance(balance) }
}