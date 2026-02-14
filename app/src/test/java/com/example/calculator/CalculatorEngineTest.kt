package com.example.calculator

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorEngineTest {

    @Test
    fun evaluates_operator_precedence() {
        assertEquals("14", CalculatorEngine.evaluate("2+3×4"))
    }

    @Test
    fun evaluates_division_with_decimal_result() {
        assertEquals("2.5", CalculatorEngine.evaluate("5÷2"))
    }

    @Test
    fun handles_negative_numbers() {
        assertEquals("-2", CalculatorEngine.evaluate("-5+3"))
    }

    @Test
    fun returns_error_for_trailing_operator() {
        assertEquals("Error", CalculatorEngine.evaluate("1+"))
    }

    @Test
    fun prevents_duplicate_decimal_in_same_segment() {
        assertEquals("1.2", CalculatorEngine.appendInput("1.2", "."))
    }
}
