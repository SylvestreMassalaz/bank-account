package fr.smassalaz.kata

import fr.smassalaz.kata.OperationType.DEPOSIT
import io.mockk.*
import org.junit.Test
import java.math.BigDecimal
import java.time.LocalDateTime

class AccountTest {

    @Test
    fun `Making a valid deposit should add the matching operation`() {
        val repository = mockk<OperationRepository>()
        every { repository.addOperation(any()) } just Runs
        val account = Account(repository)

        val amount = Amount(BigDecimal.valueOf(42))
        val date = LocalDateTime.now()

        account.deposit(amount, date)

        verify {
            repository.addOperation(Operation(DEPOSIT, date, amount))
        }
    }
}