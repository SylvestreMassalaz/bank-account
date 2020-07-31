package fr.smassalaz.kata

import fr.smassalaz.kata.OperationType.DEPOSIT
import org.junit.Test
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.assertEquals


class OperationRepositoryImplTest {

    @Test
    fun `adding an operation should enable to see it the list of existing operations`() {
        val repository = OperationRepositoryImpl()
        val operation = Operation(DEPOSIT, LocalDateTime.now(), Amount(BigDecimal.valueOf(42)))

        repository.addOperation(operation)

        assertEquals(listOf(operation), repository.getOperations())
    }
}