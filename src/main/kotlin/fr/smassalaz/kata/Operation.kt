package fr.smassalaz.kata

import java.time.LocalDateTime

data class Operation(val type: OperationType, val date: LocalDateTime, val amount: Amount)

enum class OperationType {
    DEPOSIT,
    WITHDRAWAL
}