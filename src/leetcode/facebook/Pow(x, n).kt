package leetcode.facebook

//Implement pow(x, n), which calculates x raised to the power n (i.e. xn).
//
//Example 1:
//
//Input: x = 2.00000, n = 10
//Output: 1024.00000
//Example 2:
//
//Input: x = 2.10000, n = 3
//Output: 9.26100
//Example 3:
//
//Input: x = 2.00000, n = -2
//Output: 0.25000
//Explanation: 2-2 = 1/22 = 1/4 = 0.25

fun main() {
//    println(myPow2(-1.00000, Int.MAX_VALUE))
//    println(myPow2(8.84372, -5))
    println(myPow(2.0, -2))
}

fun myPow(x: Double, n: Long): Double {
    if (n == 0L) return 1.0

    val tmp = myPow(x, n / 2)

    return if (n % 2 == 0L) {
        tmp * tmp
    } else {
        tmp * tmp * x
    }
}

fun myPow(x: Double, n: Int): Double {
    var tmpN = n.toLong()
    var tmp = x
    if(n < 0) {
        tmp = 1 / x
        tmpN = -tmpN
    }
    return myPow(tmp, tmpN)
}

fun myPow2(x: Double, n: Int): Double {
    if (x == 0.0) return 0.0
    if (n == 0) return 1.0
    if (n == 1 || x == 1.0) return x
    if (n == -1) return 1 / x
    if (x == -1.0) {
        return if (n % 2 == 0) {
            -x
        } else {
            x
        }
    }
    var tmp = x
    var tmpN = if (n < 0) n else -n

    while (tmpN % 2 == 0 && tmpN != -1) {
        tmp = tmp * tmp
        tmpN = tmpN / 2
    }


    val y = tmp
    for (i in tmpN..-2) {
        tmp = tmp * y
    }

    return if (n < 0) {
        1 / tmp
    } else {
        tmp
    }
}