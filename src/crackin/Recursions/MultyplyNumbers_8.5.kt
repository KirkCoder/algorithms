package crackin.Recursions

import kotlin.math.max
import kotlin.math.min

//8.S Recursive Multiply: Write a recursive function to multiply two positive integers without using the * operator (or / operator). You can use addition, subtraction, and bit shifting, but you should minimize the number of those operations.

fun main() {
    println(multyplyNumbers(6, 4))
    println(incrementNumber(4,4, 6))
    println(incrementNumbersSmartWithMemo(4, 6, IntArray(5)))
}


fun multyplyNumbers(a: Int, b: Int): Int {
    val small = min(a, b)
    val big = max(a, b)
    return incrementNumbersSmart(big, small)
}

fun incrementNumbersSmartWithMemo(small: Int, big: Int, memo: IntArray): Int {
    if(small == 0) return 0
    if(small == 1) return big
    val s = small shr 1
    if (memo[small] > 0) return memo[small]

    val side = incrementNumbersSmart(s, big)

    val res = if(small % 2 == 0) {
        side + side
    } else {
        side + side + big
    }
    memo[small] = res
    return res
}

fun incrementNumbersSmart(small: Int, big: Int): Int {
    if(small == 0) return 0
    if(small == 1) return big
    val s = small shr 1
    val side = incrementNumbersSmart(s, big)

    return if(small % 2 == 0) {
        side + side
    } else {
        side + incrementNumbersSmart(small - s, big)
    }
}

fun incrementNumber(target:Int, a: Int, b: Int): Int {
    return if(b > 1) {
        incrementNumber(target, a + target, b - 1)
    } else {
        a
    }
}