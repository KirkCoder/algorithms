package tasks

import java.lang.IllegalStateException
import java.util.*

fun main() {
    println(calculator("2-6-7*8/2+5"))
    println(calculateWithBaskets("2-(6-5)*8/2+5"))
}

fun calculateWithBaskets(variabl: String): Double {
    val chars = variabl.toCharArray()
    val numbers: Stack<Double> = Stack()
    val operators: Stack<Char> = Stack()
    var i = 0
    while (i != chars.size) {
        val char = chars[i]
        if (isOperator(char) || isOpenBasket(char) || isCloseBasket(char)) {
            if (operators.isEmpty() || isOpenBasket(char)) {
                operators.push(char)
                i++
            } else {
                if (isCloseBasket(char)) {
                    var prev = operators.peek()
                    while (!isOpenBasket(prev)) {
                        numbers.push(calculate(numbers.pop(), numbers.pop(), operators.pop()))
                        prev = operators.peek()
                    }
                    operators.pop()
                    i++
                } else {
                    if (isOpenBasket(operators.peek())) {
                        operators.push(char)
                        i++
                    } else {
                        if (getOperatorPriority(operators.peek()) >= getOperatorPriority(char)) {
                            numbers.push(calculate(numbers.pop(), numbers.pop(), operators.pop()))
                        } else {
                            operators.push(char)
                            i++
                        }
                    }
                }
            }
        } else {
            numbers.push(char.toString().toDouble())
            i++
        }
    }
    while (operators.isNotEmpty()) {
        numbers.push(calculate(numbers.pop(), numbers.pop(), operators.pop()))
    }
    return numbers.pop()
}

fun calculator(variabl: String): Double {
    val chars = variabl.toCharArray()
    val numbers: Stack<Double> = Stack()
    val operators: Stack<Char> = Stack()
    var i = 0
    while (i != chars.size) {
        val char = chars[i]
        if (isOperator(char)) {
            if (operators.isEmpty()) {
                operators.push(char)
                i++
            } else {
                if (getOperatorPriority(operators.peek()) >= getOperatorPriority(char)) {
                    numbers.push(calculate(numbers.pop(), numbers.pop(), operators.pop()))
                } else {
                    operators.push(char)
                    i++
                }
            }
        } else {
            numbers.push(char.toString().toDouble())
            i++
        }
    }
    while (operators.isNotEmpty()) {
        numbers.push(calculate(numbers.pop(), numbers.pop(), operators.pop()))
    }
    return numbers.pop()
}

fun calculate(y: Double, x: Double, o: Char): Double {
    return when (o) {
        P -> x + y
        M -> x - y
        MU -> x * y
        D -> x / y
        else -> throw IllegalStateException("not valid operator input")
    }
}

fun isOperator(o: Char) = o == P || o == M || o == MU || o == D

fun getOperatorPriority(o: Char): Int {
    return when (o) {
        P, M -> 1
        MU, D -> 2
        else -> throw IllegalStateException("not valid operator input")
    }
}

fun isOpenBasket(o: Char) = o == O
fun isCloseBasket(o: Char) = o == C

const val P = '+'
const val M = '-'
const val MU = '*'
const val D = '/'
const val O = '('
const val C = ')'