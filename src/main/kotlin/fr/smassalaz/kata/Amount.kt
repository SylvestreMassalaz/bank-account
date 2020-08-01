package fr.smassalaz.kata

import java.math.BigDecimal

data class Amount(val value: BigDecimal) : Comparable<Amount> {

    fun isNegative() = value < BigDecimal.ZERO

    override fun compareTo(other: Amount): Int = value.compareTo(other.value)

    operator fun plus(increment: Amount) = Amount(value + increment.value)

    operator fun minus(decrement: Amount) = Amount(value - decrement.value)

    companion object {
        val ZERO = Amount(BigDecimal.ZERO)
    }
}