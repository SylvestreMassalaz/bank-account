package fr.smassalaz.kata

import java.time.LocalDateTime

data class Statement(val type: OperationType, val date: LocalDateTime, val amount: Amount, val balance: Amount)