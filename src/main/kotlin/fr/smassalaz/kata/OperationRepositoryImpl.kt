package fr.smassalaz.kata

class OperationRepositoryImpl(initialOperations: List<Operation> = listOf()) : OperationRepository {

    private val operations = initialOperations.toMutableList()

    override fun getOperations(): List<Operation> = operations.toList()

    override fun addOperation(operation: Operation) {
        operations += operation
    }
}