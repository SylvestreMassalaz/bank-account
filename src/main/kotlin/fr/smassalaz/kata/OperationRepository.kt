package fr.smassalaz.kata

interface OperationRepository {
    fun getOperations(): List<Operation>

    fun addOperation(operation: Operation)
}