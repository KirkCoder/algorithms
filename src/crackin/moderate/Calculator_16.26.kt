package crackin.moderate

import java.util.*

//Calculator:Givenanarithmeticequationconsistingofpositiveintegers,+,-,*and/(noparentheses), compute the result.
//EXAMPLE
//Input: 2*3+5/6*3+15 Output: 23.5

fun main() {
    println(calculate("2*3+5/6*3+15"))
    println(calculate("3+6*2"))
    println(calculate("2-6-7*8/2+5"))
}

fun calculate(values: String): Double {
    val vals = values.toCharArray()
    val numbers = LinkedList<Double>()
    val operators = LinkedList<Char>()
    var lastWasNumber = false

    for (c in vals) {
        if (isOperator(c)) {
            lastWasNumber = false
            calculate(operators, c, numbers)
        } else {
            if (lastWasNumber) {
                val last = numbers.removeLast()
                numbers.add("${last.toLong()}${c}".toDouble())
            } else {
                numbers.add(c.toString().toDouble())
            }
            lastWasNumber = true
        }
    }

    for (i in 0 until operators.size) {
        val operator = operators.removeLast()
        val second = numbers.removeLast()
        val first = numbers.removeLast()
        numbers.add(calculate(first, second, operator))
    }

    if (numbers.size > 1) throw Exception()

    return numbers.last
}

private fun calculate(
    operators: LinkedList<Char>,
    c: Char,
    numbers: LinkedList<Double>
) {
    val lastOperator = operators.lastOrNull()
    if (lastOperator?.isSameOrMore(c) == true) {
        val second = numbers.removeLast()
        val first = numbers.removeLast()
        operators.removeLast()
        numbers.add(calculate(first, second, lastOperator))
        calculate(operators, c, numbers)
    } else {
        operators.add(c)
    }
}

fun isOperator(c: Char): Boolean {
    return c == '+' || c == '-' || c == '*' || c == '/'
}

fun calculate(first: Double, second: Double, operator: Char): Double {
    return when (operator) {
        '+' -> first + second
        '-' -> first - second
        '*' -> first * second
        else -> first / second
    }
}

fun Char.isSameOrMore(second: Char): Boolean {
    if (!isOperator(this) || !isOperator(second)) {
        throw Exception()
    }
    return if (this == '+' || this == '-') {
        !(second == '*' || second == '/')
    } else {
        true
    }
}