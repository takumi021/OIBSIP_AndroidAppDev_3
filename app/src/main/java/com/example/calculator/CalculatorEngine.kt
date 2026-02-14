package com.example.calculator

import java.math.BigDecimal
import java.math.RoundingMode

internal object CalculatorEngine {
    private val operators = setOf('+', '-', '*', '/', '%')

    fun appendInput(current: String, input: String): String {
        if (current == "Error") return if (input in "0123456789.") input else "0"

        if (input == ".") {
            val lastSegment = current.split('+', '-', '×', '÷', '%').last()
            if (lastSegment.contains('.')) return current
            if (lastSegment.isEmpty() || lastSegment == "-") return "$current0."
        }

        if (current == "0" && input.firstOrNull()?.isDigit() == true) return input

        if (input.firstOrNull() in setOf('+', '-', '×', '÷', '%') && current.lastOrNull() in setOf('+', '-', '×', '÷', '%')) {
            return current.dropLast(1) + input
        }

        return current + input
    }

    fun handleAction(current: String, input: String): String = when (input) {
        "AC" -> "0"
        "⌫" -> if (current.length <= 1 || current == "Error") "0" else current.dropLast(1)
        "=" -> evaluate(current)
        else -> appendInput(current, input)
    }

    fun evaluate(expression: String): String {
        val normalized = expression.replace('×', '*').replace('÷', '/')
        if (normalized.isBlank() || normalized.last() in operators) return "Error"

        return try {
            val tokens = tokenize(normalized)
            val result = evaluateTokens(tokens)
            formatResult(result)
        } catch (_: Exception) {
            "Error"
        }
    }

    private fun tokenize(expression: String): List<String> {
        val tokens = mutableListOf<String>()
        val number = StringBuilder()

        expression.forEachIndexed { index, char ->
            when {
                char.isDigit() || char == '.' -> number.append(char)
                char == '-' && (index == 0 || expression[index - 1] in operators) -> number.append(char)
                char in operators -> {
                    if (number.isNotEmpty()) {
                        tokens += number.toString()
                        number.clear()
                    }
                    tokens += char.toString()
                }
                else -> throw IllegalArgumentException("Invalid character: $char")
            }
        }

        if (number.isNotEmpty()) tokens += number.toString()
        return tokens
    }

    private fun evaluateTokens(tokens: List<String>): BigDecimal {
        val numbers = mutableListOf<BigDecimal>()
        val ops = mutableListOf<String>()

        fun precedence(operator: String): Int = when (operator) {
            "+", "-" -> 1
            "*", "/", "%" -> 2
            else -> 0
        }

        fun applyTopOperator() {
            val right = numbers.removeLast()
            val left = numbers.removeLast()
            val operator = ops.removeLast()
            val value = when (operator) {
                "+" -> left + right
                "-" -> left - right
                "*" -> left * right
                "/" -> left.divide(right, 10, RoundingMode.HALF_UP)
                "%" -> left.remainder(right)
                else -> error("Unsupported operator")
            }
            numbers += value
        }

        tokens.forEach { token ->
            token.toBigDecimalOrNull()?.let {
                numbers += it
            } ?: run {
                while (ops.isNotEmpty() && precedence(ops.last()) >= precedence(token)) {
                    applyTopOperator()
                }
                ops += token
            }
        }

        while (ops.isNotEmpty()) applyTopOperator()
        return numbers.last()
    }

    private fun formatResult(value: BigDecimal): String {
        val stripped = value.stripTrailingZeros()
        return if (stripped.scale() <= 0) {
            stripped.toPlainString()
        } else {
            stripped.setScale(minOf(stripped.scale(), 8), RoundingMode.HALF_UP)
                .stripTrailingZeros()
                .toPlainString()
        }
    }
}
