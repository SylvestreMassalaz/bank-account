package features

import fr.smassalaz.kata.*
import fr.smassalaz.kata.Account
import fr.smassalaz.kata.Amount
import fr.smassalaz.kata.Operation
import fr.smassalaz.kata.OperationType
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import java.math.BigDecimal
import java.time.LocalDateTime

class AccountStepDefs : En {

    private lateinit var operationRepository: OperationRepository

    private lateinit var statementPrinter: StatementPrinterTestImpl

    private lateinit var account: Account

    private var caughtExceptions = mutableListOf<Exception>()

    init {
        Given("There is an account with the following operations") { table: DataTable ->
            val operations = table.asMaps().map {
                Operation(
                    type = OperationType.valueOf(it.getValue("type")),
                    date = it.getValue("date").toLocalDateTime(),
                    amount = Amount(it.getValue("amount").toBigDecimal())
                )
            }

            operationRepository = OperationRepositoryImpl(operations)

            statementPrinter = StatementPrinterTestImpl()

            account = Account(operationRepository, statementPrinter)

            caughtExceptions = mutableListOf()
        }

        When("I deposit an amount of {bigdecimal} at date {int}-{int}-{int} {int}:{int}")
        { amount: BigDecimal, year: Int, month: Int, day: Int, hour: Int, minute: Int ->
            try {
                account.deposit(
                    amount = Amount(amount),
                    date = LocalDateTime.of(year, month, day, hour, minute)
                )
            } catch (e: Exception) {
                caughtExceptions.add(e)
            }
        }

        When("I withdraw an amount of {bigdecimal} at date {int}-{int}-{int} {int}:{int}")
        { amount: BigDecimal, year: Int, month: Int, day: Int, hour: Int, minute: Int ->
            try {
                account.withdraw(
                    amount = Amount(amount),
                    date = LocalDateTime.of(year, month, day, hour, minute)
                )
            } catch (e: Exception) {
                caughtExceptions.add(e)
            }
        }

        When("The user prints it's statements") {
            account.printStatements()
        }

        Then("The operation should be accepted") {
            assertTrue(caughtExceptions.isEmpty())
        }

        Then("The operation should be rejected") {
            assertTrue(caughtExceptions.isNotEmpty())
        }

        Then("The account should have the following operations") { table: DataTable ->
            val expected: List<Operation> = table.asMaps().map {
                Operation(
                    type = OperationType.valueOf(it.getValue("type")),
                    date = it.getValue("date").toLocalDateTime(),
                    amount = Amount(it.getValue("amount").toBigDecimal())
                )
            }

            assertEquals(expected, operationRepository.getOperations())
        }

        Then("The statement printing should have the following lines") { table: DataTable ->
            val expected: List<Statement> = table.asMaps().map {
                Statement(
                    type = OperationType.valueOf(it.getValue("type")),
                    date = it.getValue("date").toLocalDateTime(),
                    amount = Amount(it.getValue("amount").toBigDecimal()),
                    balance = Amount(it.getValue("balance").toBigDecimal())
                )
            }

            assertEquals(expected, statementPrinter.statements)
        }
    }
}