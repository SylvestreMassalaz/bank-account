package fr.smassalaz.kata.business

import fr.smassalaz.kata.business.Operation

interface OperationRepository {
    fun getOperations(): List<Operation>

    fun addOperation(operation: Operation)
}