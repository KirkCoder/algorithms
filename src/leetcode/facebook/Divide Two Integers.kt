package leetcode.facebook

//Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
//
//Return the quotient after dividing dividend by divisor.
//
//The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
//
//Note:
//
//Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For this problem, assume that your function returns 231 − 1 when the division result overflows.
//
//
//Example 1:
//
//Input: dividend = 10, divisor = 3
//Output: 3
//Explanation: 10/3 = truncate(3.33333..) = 3.
//Example 2:
//
//Input: dividend = 7, divisor = -3
//Output: -2
//Explanation: 7/-3 = truncate(-2.33333..) = -2.
//Example 3:
//
//Input: dividend = 0, divisor = 1
//Output: 0
//Example 4:
//
//Input: dividend = 1, divisor = 1
//Output: 1
//
//
//Constraints:
//
//-231 <= dividend, divisor <= 231 - 1
//divisor != 0

fun main() {
    println(divide2(-2147483648, 2))
}

fun divide2(dividend: Int, divisor: Int): Int {
    if(dividend == Int.MIN_VALUE && divisor == -1) {
        return Int.MAX_VALUE
    }

    if(dividend == Int.MIN_VALUE && divisor == 1) {
        return Int.MIN_VALUE
    }

    if(dividend == 0 || divisor == 0) {
        return 0
    }
    var f = if(dividend < 0) dividend else -dividend
    var s = if(divisor < 0) divisor else -divisor
    var res = 0

    while(f <= s) {
        var tmpRes = -1
        var tmpS = s
        while(f <= tmpS + tmpS && tmpS >= Int.MIN_VALUE / 2) {
            tmpS += tmpS
            tmpRes += tmpRes
        }
        res += tmpRes
        f -= tmpS
    }

    return if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
        -res
    } else {
        res
    }
}

fun divide(dividend: Int, divisor: Int): Int {
    if(dividend == Int.MIN_VALUE && divisor == -1) {
        return Int.MAX_VALUE
    }

    if(dividend == Int.MIN_VALUE && divisor == 1) {
        return Int.MIN_VALUE
    }

    if(dividend == 0 || divisor == 0) {
        return 0
    }
    var f = dividend.toLong()
    var s = divisor.toLong()
    var changeSign = false
    var res = 0

    if(f < 0) {
        f = (f xor -1) + 1
        changeSign = !changeSign
    }
    if(s < 0) {
        s = (s xor -1) + 1
        changeSign = !changeSign
    }

    while(f >= s) {
        f = f - s
        res++
    }

    if(changeSign) {
        res = (res xor -1) + 1
    }

    return res
}