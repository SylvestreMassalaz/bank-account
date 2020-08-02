package fr.smassalaz.kata.business

import fr.smassalaz.kata.business.Statement

interface StatementPrinter {
    fun printStatements(statements: List<Statement>)
}