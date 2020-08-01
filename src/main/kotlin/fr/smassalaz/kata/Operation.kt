package fr.smassalaz.kata

import java.time.LocalDateTime

data class Operation(val type: OperationType, val date: LocalDateTime, val amount: Amount) {
    fun computeBalance(old: Amount): Amount = type.apply(old, amount)

    companion object {
        fun deposit(date: LocalDateTime, amount: Amount) =
            Operation(OperationType.DEPOSIT, date, amount)

        fun withdrawal(date: LocalDateTime, amount: Amount) =
            Operation(OperationType.WITHDRAWAL, date, amount)
    }
}

enum class OperationType(val apply: (balance: Amount, amount: Amount) -> Amount) {
    DEPOSIT({ balance, amount -> balance + amount }),
    WITHDRAWAL({ balance, amount -> balance - amount })
}