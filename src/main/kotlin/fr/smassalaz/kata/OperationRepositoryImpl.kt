package fr.smassalaz.kata

class OperationRepositoryImpl(initialOperations: List<Operation> = listOf()) : OperationRepository {

    private val operations = initialOperations.toMutableList()

    override fun getOperations(): List<Operation> {
        TODO("Not yet impÈlemented")
    }

    override fun addOperation(operation: Operation) {
        TODO("Not yet implemented")
    }
}