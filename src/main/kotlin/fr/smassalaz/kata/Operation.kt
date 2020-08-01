package fr.smassalaz.kata

import java.time.LocalDateTime

data class Operation(val type: OperationType, val date: LocalDateTime, val amount: Amount) {
    fun computeBalance(old: Amount): Amount = type.apply(old, amount)
}

enum class OperationType(val apply: (Amount, Amount) -> Amount) {
    DEPOSIT({ balance, amount -> balance + amount }),
    WITHDRAWAL({ balance, amount -> balance - amount })
}