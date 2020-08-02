package fr.smassalaz.kata.business

import fr.smassalaz.kata.business.Amount
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AmountTest {

    @Test
    fun `isNegative should return true when the amount is negative`() {
        assertFalse(Amount(BigDecimal.valueOf(0)).isNegative())
        assertFalse(Amount(BigDecimal.valueOf(10)).isNegative())
        assertTrue(Amount(BigDecimal.valueOf(-0.1)).isNegative())
    }

    @Test
    fun `addition should work`() {
        val amount1 = Amount(BigDecimal.valueOf(1))
        val amount2 = Amount(BigDecimal.valueOf(2))

        assertEquals(Amount(BigDecimal.valueOf(3)), amount1 + amount2)
    }

    @Test
    fun `subtraction should work`() {
        val amount1 = Amount(BigDecimal.valueOf(1))
        val amount2 = Amount(BigDecimal.valueOf(2))

        assertEquals(Amount(BigDecimal.valueOf(-1)), amount1 - amount2)
    }

    @Test
    fun `Comparing two amounts should work as intended`() {
        val amount1 = Amount(BigDecimal.valueOf(1))
        val amount2 = Amount(BigDecimal.valueOf(2))
        val amount3 = Amount(BigDecimal.valueOf(-2))

        assertTrue(amount1 < amount2)
        assertFalse(amount1 > amount2)
        assertTrue(amount3 < amount1)
        assertTrue(amount3 < amount2)
        assertFalse(amount3 > amount1)
        assertFalse(amount3 > amount2)
    }
}