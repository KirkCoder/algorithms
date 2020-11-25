package crackin.moderate

//Factorial Zeros: Write an algorithm which computes the number of trailing zeros in n factorial.

fun main() {
    println(zerosInFactorial(10))
    println(zerosInFactorialSmart(10))
}

fun zerosInFactorialSmart(n: Int): Int {
    var i = 5
    var count = 0
    while (n / i > 0) {
        count += n / i
        i *= 5
    }
    return count
}

fun zerosInFactorial(n: Int): Int {
    var f = factorial(n.toLong())
    var count = 0
    while (f % 10L == 0L) {
        count++
        f /= 10
    }
    return count
}

fun factorial(n: Long): Long {
    if (n <= 1) return 1
    return n * factorial(n - 1)
}