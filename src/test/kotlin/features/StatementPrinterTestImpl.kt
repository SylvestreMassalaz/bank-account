package features

import fr.smassalaz.kata.business.Statement
import fr.smassalaz.kata.business.StatementPrinter

class StatementPrinterTestImpl: StatementPrinter {

    lateinit var statements: List<Statement>
    override fun printStatements(statements: List<Statement>) {
       this.statements = statements
    }
}