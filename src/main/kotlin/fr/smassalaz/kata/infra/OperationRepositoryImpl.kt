package fr.smassalaz.kata.infra

import fr.smassalaz.kata.business.Operation
import fr.smassalaz.kata.business.OperationRepository
import fr.smassalaz.kata.business.OperationTooOldException

class OperationRepositoryImpl(initialOperations: List<Operation> = listOf()) :
    OperationRepository {

    private val operations = initialOperations.toMutableList()

    override fun getOperations(): List<Operation> = operations.toList()

    override fun addOperation(operation: Operation) {
        if(operations.isNotEmpty() && operations.last().date > operation.date) {
            throw OperationTooOldException()
        }
        operations += operation
    }
}