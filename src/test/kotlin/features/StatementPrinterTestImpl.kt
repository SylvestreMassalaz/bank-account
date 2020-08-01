package features

import fr.smassalaz.kata.Statement
import fr.smassalaz.kata.StatementPrinter

class StatementPrinterTestImpl: StatementPrinter {

    lateinit var statements: List<Statement>
    override fun printStatements(statements: List<Statement>) {
       this.statements = statements
    }
}