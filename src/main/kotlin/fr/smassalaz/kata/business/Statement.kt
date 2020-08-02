package fr.smassalaz.kata.business

import fr.smassalaz.kata.business.Amount
import fr.smassalaz.kata.business.OperationType
import java.time.LocalDateTime

data class Statement(val type: OperationType, val date: LocalDateTime, val amount: Amount, val balance: Amount)