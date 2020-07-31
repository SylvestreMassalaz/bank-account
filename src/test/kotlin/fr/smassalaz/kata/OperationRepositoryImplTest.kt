package fr.smassalaz.kata

import fr.smassalaz.kata.OperationType.DEPOSIT
import org.junit.Test
import java.lang.Exception
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

    @Test(expected = OperationTooOldException::class)
    fun `Adding an operation that is before the last registered one should fail`() {
        val oldOperation = Operation(DEPOSIT, LocalDateTime.now(), Amount(BigDecimal.valueOf(42)))
        val operation1 = Operation(DEPOSIT, LocalDateTime.now().minusMinutes(5), Amount(BigDecimal.valueOf(42)))
        val repository = OperationRepositoryImpl(listOf(oldOperation))

        try {
            repository.addOperation(operation1)
        } catch (e: Exception) {
            assertEquals(listOf(oldOperation), repository.getOperations())
            throw e
        }

    }
}