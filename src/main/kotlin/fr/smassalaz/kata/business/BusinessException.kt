package fr.smassalaz.kata.business

open class BusinessException(message: String): Exception(message)

class NegativeAmountException(): BusinessException("You can't have an operation with a negative amount")

class OperationTooOldException : BusinessException("This operation is too old to be added")

class InsufficientFundsException : BusinessException("Not enough funds")