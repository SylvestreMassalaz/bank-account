package fr.smassalaz.kata

import fr.smassalaz.kata.OperationType.DEPOSIT
import fr.smassalaz.kata.OperationType.WITHDRAWAL
import io.mockk.*
import org.junit.Test
import java.math.BigDecimal.valueOf
import java.time.LocalDateTime

class AccountTest {

    @Test
    fun `Making a valid deposit should add the matching operation`() {
        val repository = mockk<OperationRepository>()
        every { repository.addOperation(any()) } just Runs
        val account = Account(repository)

        val amount = Amount(valueOf(42))
        val date = LocalDateTime.now()

        account.deposit(amount, date)

        verify {
            repository.addOperation(Operation(DEPOSIT, date, amount))
        }
    }

    @Test(expected = NegativeAmountException::class)
    fun `Making a deposit of a negative amount of money should be invalid`() {
        val repository = mockk<OperationRepository>()
        every { repository.addOperation(any()) } just Runs
        val account = Account(repository)

        val amount = Amount(valueOf(-42))
        val date = LocalDateTime.now()

        try {
            account.deposit(amount, date)
        } catch (e: Exception) {
            verify(exactly = 0) {
                repository.addOperation(Operation(DEPOSIT, date, amount))
            }

            throw e
        }
    }

    @Test
    fun `Making a withdrawal of an account with sufficient funds should add a withdrawal operation`() {
        val repository = mockk<OperationRepository>()
        every { repository.addOperation(any()) } just Runs
        val account = Account(repository)
        val amount = Amount(valueOf(42))
        val date = LocalDateTime.now()
        every { repository.getOperations() } returns listOf(
            Operation(DEPOSIT, LocalDateTime.now().minusMinutes(5), Amount(valueOf(42)))
        )

        account.withdraw(amount, date)

        verify {
            repository.addOperation(Operation(WITHDRAWAL, date, amount))
        }
    }

    @Test(expected = InsufficientFundsException::class)
    fun `Making a withdrawal of an account without sufficient funds should add a withdrawal operation`() {
        val repository = mockk<OperationRepository>()

        every { repository.addOperation(any()) } just Runs
        every { repository.getOperations() } returns listOf(
            Operation(DEPOSIT, LocalDateTime.now().minusMinutes(5), Amount(valueOf(42)))
        )
        val account = Account(repository)

        val amount = Amount(valueOf(42.1))
        val date = LocalDateTime.now()

        try {
            account.withdraw(amount, date)
        } catch (e: Exception) {
            verify(exactly = 0) {
                repository.addOperation(Operation(WITHDRAWAL, date, amount))
            }
            throw e
        }
    }

    @Test(expected = NegativeAmountException::class)
    fun `Making a withdrawal of a negative amount of money should be invalid`() {
        val repository = mockk<OperationRepository>()

        every { repository.addOperation(any()) } just Runs
        val account = Account(repository)

        val amount = Amount(valueOf(-42))
        val date = LocalDateTime.now()

        try {
            account.withdraw(amount, date)
        } catch (e: Exception) {
            verify(exactly = 0) {
                repository.addOperation(Operation(WITHDRAWAL, date, amount))
            }

            throw e
        }
    }

    @Test
    fun `An empty account should print no statements`() {
        val repository = mockk<OperationRepository>()
        val printer = mockk<StatementPrinter>()

        every { printer.printStatements(any()) } just Runs
        every { repository.getOperations() } returns listOf()

        val account = Account(repository)

        account.printStatements(printer)

        verify(exactly = 1) {
            printer.printStatements(listOf())
        }
    }

    @Test
    fun `A non empty account should print the right statements with the correct balance`() {
        val repository = mockk<OperationRepository>()
        val printer = mockk<StatementPrinter>()

        val baseDate = LocalDateTime.of(2020, 7, 1, 9, 0)

        every { printer.printStatements(any()) } just Runs
        every { repository.getOperations() } returns listOf(
            Operation.deposit(baseDate, Amount(valueOf(2500))),
            Operation.withdrawal(baseDate.plusMinutes(30), Amount(valueOf(770)))
        )

        val account = Account(repository)

        account.printStatements(printer)

        verify(exactly = 1) {
            printer.printStatements(
                listOf(
                    Statement(DEPOSIT, baseDate, Amount(valueOf(2500)), Amount(valueOf(2500))),
                    Statement(WITHDRAWAL, baseDate.plusMinutes(30), Amount(valueOf(770)), Amount(valueOf(1730)))
                )
            )
        }
    }
}