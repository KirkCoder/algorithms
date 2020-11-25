package crackin.moderate

import java.lang.Exception
import kotlin.math.abs

fun main() {
    println(minus(4, -7))
    println(multyply(2, 5))
    println(divide(-100, 50))
}

fun divide(a: Int, b: Int): Int {
    if (b == 0) throw Exception()
    var tmpA = abs(a)
    var tmpB = abs(b)

    var res = 0
    if (tmpA < tmpB) {
        return 0
    }
    tmpB = negate(tmpB)
    while (tmpA > 0) {
        tmpA += tmpB
        res++
    }
    return if ((a > 0 && b < 0) || (a < 0 && b > 0)) negate(res) else res
}


fun minus(a: Int, b: Int): Int {
    return a + negate(b)
}

fun multyply(a: Int, b: Int): Int {
    val tmpA: Int
    val tmpB: Int
    when {
        a < 0 && b > 0 -> {
            tmpA = a
            tmpB = b
        }
        (a > 0 && b < 0) || (a < 0 && b < 0) -> {
            tmpA = -a
            tmpB = -b
        }
        else -> {
            tmpA = a
            tmpB = b
        }
    }
    var res = 0
    for (i in 1..tmpB) {
        res += tmpA
    }
    return res
}

fun negate(a: Int): Int {
    val neg = if (a > 0) -1 else 1
    var res = 0
    var tmp = a
    while (tmp != 0) {
        tmp += neg
        res += neg
    }
    return res
}