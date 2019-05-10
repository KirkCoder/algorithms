package tasks

fun main() {
    val n = 18
    val res = getFactorial(n)
    println(res)
    println(getZeros(res))
    println(getZerosSmart(n))
}

fun getZerosSmart(n: Int): Int {
    var i = 5
    var count = 0
    while (n / i > 0) {
        count += n / i
        i *= 5
    }
    return count
}

fun getZeros(i: Long): Long {
    var tmp = i
    var count = 0L
    while (tmp % 10L == 0L) {
        tmp /= 10L
        count++
    }
    return count
}

fun getFactorial(n: Int): Long {
    var tmp = 1
    var res = 1L
    while (tmp <= n) {
        res *= tmp
        tmp++
    }
    return res
}
