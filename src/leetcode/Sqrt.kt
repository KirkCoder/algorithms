package leetcode

fun main() {
//    1
//    1 + 1
//    println(mSqrt(8))
//    println(mSqrt(16))
//    println(mSqrt(4))
//    println(mSqrt(10))
//    println(mSqrt(3))
//    println(mSqrt(7))
//    println(mSqrt(2147483647))
}

fun mySqrt(x: Int): Int {
    if (x < 2) return x
    val left = Math.pow(Math.E, 0.5 * Math.log(x.toDouble())).toInt()
    val right = left + 1
    return if (right.toLong() * right > x) left else right
}

fun mSqrt(x: Int): Int {
    if(x == 0) return 0
    if(x == 1) return 1
    var tmp = x.toLong()
    val tmpX = x.toLong()
    var res = 0L
    while(res * res < tmpX) {
        if(tmp % 2L == 0L) {
            res += 1L
        } else {
            res += 2L
        }
        tmp = tmp / 2L
    }
    while (res * res > tmpX) {
        res--
    }
    return res.toInt()
}