package com.example.calculator

object CalculatorEngine {
    fun handleAction(expression: String, label: String): String {
        return when (label) {
            "AC" -> "0"
            "⌫" -> if (expression.length > 1) expression.dropLast(1) else "0"
            "=" -> evaluateExpression(expression)
            else -> if (expression == "0") label else expression + label
        }
    }

    private fun evaluateExpression(expression: String): String {
        return try {
            val result = expression.split("+", "-", "×", "÷").map { it.toDouble() }
            val operators = expression.filter { it in listOf('+', '-', '×', '÷') }

            var currentResult = result.first()
            for (i in operators.indices) {
                currentResult = when (operators[i]) {
                    '+' -> currentResult + result[i + 1]
                    '-' -> currentResult - result[i + 1]
                    '×' -> currentResult * result[i + 1]
                    '÷' -> currentResult / result[i + 1]
                    else -> 0.0
                }
            }
            currentResult.toString()
        } catch (e: Exception) {
            "Error"
        }
    }
}