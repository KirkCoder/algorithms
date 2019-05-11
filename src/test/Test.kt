package test

import tasks.sout
import java.util.*

fun main() {

    val operators: Stack<Char> = Stack()
    operators.push('a')
    operators.push('b')
    operators.push('c')

    for (operator in operators) {
        print(operator)
    }
    println()

    StringTokenizer("d")

    println(operators.peek())

    for (operator in operators) {
        print(operator)
    }
}