package crackin.Recursions

import showList
import java.lang.Exception
import java.util.*
import kotlin.math.exp

//Boolean Evaluation: Given a boolean expression consisting of the symbols e (false), 1 (true), & (AND), I (OR), and A(XOR), and a desired boolean result value result, implement a function to countthenumberofwaysofparenthesizingtheexpressionsuchthatitevaluatesto result.The expressionshouldbefullyparenthesized(e.g.,(e)A(1» butnotextraneously(e.g.,(((e» A(1)».
//EXAMPLE
//countEval("1A alaI1", false) -> 2
//countEval("a&a&a&1A 1Ia", true) - > 1a


fun main() {
    println(countDesisions("1^0|0|1", false))
    println(countSmart("1^0|0|1", false))
//    println(countDesisions("0&0&0&1^1|0", true))
//    println(countDesisions("0^0&0^1|1", true))
}

fun countSmart(expression: String, result: Boolean): Int {
    if (expression.isEmpty()) return 0
    if (expression.length == 1) return if (expression.toBoolean() == result) 1 else 0

    var i = 1
    var count = 0
    while (i < expression.length) {
        val c = expression[i]
        val leftTrue = countSmart(expression.substring(0, i), true)
        val leftFalse = countSmart(expression.substring(0, i), false)
        val rightTrue = countSmart(expression.substring(i + 1, expression.length), true)
        val rightFalse = countSmart(expression.substring(i + 1, expression.length), false)
        val total = (leftFalse + leftTrue) * (rightFalse + rightTrue)
        var tmpCount = 0
        when (c) {
            '^' -> tmpCount = (leftTrue * rightFalse) + (rightTrue * leftFalse)
            '|' -> tmpCount = (leftTrue * rightFalse) + (rightTrue * leftFalse) + (leftTrue * rightTrue)
            '&' -> tmpCount = leftTrue * rightTrue
        }
        count += if (result) tmpCount else total - tmpCount
        i += 2
    }
    return count
}

fun String.toBoolean(): Boolean {
    return this == "1"
}

fun countDesisions(expression: String, result: Boolean): Int {
    val chars = expression.toCharArray().toList()
    val expressions = parenthesize(chars)
    expressions.map { it to parseExpression(it) }.filter {
        if (result) it.second == 1 else it.second == 0
    }.forEach {
        println("${it.first.joinToString("")} -> ${it.second}")
    }
    return expressions.mapNotNull { parseExpression(it) }.filter {
        if (result) it == 1 else it == 0
    }.size
}

fun parenthesize(chars: List<Char>): List<LinkedList<Char>> {
    val expressions = mutableListOf<LinkedList<Char>>()
    parenthesize2(chars, 0, false, expressions)
    showList(expressions.map { it.joinToString("") })
    return expressions
}

fun parseExpression(expression: List<Char>): Int? {
    var first: Int? = null
    var sign: Char? = null
    var i = 0
    while (i < expression.size) {
        if (expression[i] == '(') {
            var j = i
            var close: Char? = null
            while (close != ')' && j < expression.size) {
                close = expression[j]
                j++
            }
            if (close != null) {
                first = if (first == null) {
                    calculateInBaskets(i, j, expression)
                } else {
                    calculate(first, calculateInBaskets(i, j, expression), sign)
                }
                i = j
            }
        }
        if (i < expression.size && expression[i].toString().toIntOrNull() != null) {
            val second = expression[i].toString().toInt()
            first = if (first == null) {
                second
            } else {
                calculate(first, second, sign)
            }
        }

        if (i < expression.size && expression[i].toString().toIntOrNull() == null && expression[i] != '(' && expression[i] != ')') {
            sign = expression[i]
        }
        i++
    }
    return first
}

fun calculateInBaskets(i: Int, j: Int, expression: List<Char>): Int {
    var first: Int? = null
    var sign: Char? = null
    var k = i + 1
    while (k < j) {
        if (expression[k].toString().toIntOrNull() != null) {
            val second = expression[k].toString().toInt()
            first = if (first == null) {
                second
            } else {
                calculate(first, second, sign)
            }
        }

        if (expression[k].toString().toIntOrNull() == null && expression[k] != '(' && expression[k] != ')') {
            sign = expression[k]
        }
        k++
    }
    return first!!
}

fun calculate(first: Int?, second: Int?, sign: Char?): Int {
    if (first == null || second == null || sign == null) throw Exception()
    return when (sign) {
        '^' -> first xor second
        '|' -> first or second
        '&' -> first and second
        else -> throw Exception()
    }
}

fun parenthesize2(chars: List<Char>, index: Int, isClose: Boolean, expressions: MutableList<LinkedList<Char>>) {

    var res = chars.toLinkedList()
    for (j in index until chars.size) {
        if (chars[j].toString().toIntOrNull() != null && !isClose) {
            res.add(j, '(')
            parenthesize2(res, j + 3, true, expressions)
            res = chars.toLinkedList()
        }
        if (chars[j].toString().toIntOrNull() == null && isClose) {
            res.add(j, ')')
            expressions.add(res)
            parenthesize2(res, j + 1, false, expressions)
            res = chars.toLinkedList()
        }
        if (isClose && j == chars.size - 1) {
            res.add(')')
            expressions.add(res)
        }
    }
}

fun parenthesize(chars: List<Char>, index: Int, isClose: Boolean, expressions: MutableList<LinkedList<Char>>) {

    var res = chars.toLinkedList()
    for (j in index until chars.size) {
        if (chars[j].toString().toIntOrNull() != null && !isClose) {
            res.add(j, '(')
            parenthesize(res, j + 1, true, expressions)
            res = chars.toLinkedList()
        }
        if (chars[j].toString().toIntOrNull() == null && isClose) {
            res.add(j, ')')
            expressions.add(res)
            parenthesize(res, j + 1, false, expressions)
            res = chars.toLinkedList()
        }
        if (isClose && j == chars.size - 1) {
            res.add(')')
            expressions.add(res)
        }
    }
}

fun List<Char>.toLinkedList(): LinkedList<Char> {
    val list = LinkedList<Char>()
    for (c in this) {
        list.add(c)
    }
    return list
}