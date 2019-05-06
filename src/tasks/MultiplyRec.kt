fun main() {
    val a = 78
    val b = 7
    println("${a * b} == ${multiply(a, b)}")
}

fun multiply(a: Int, b: Int): Int {
    return if (a < b) {
        calculate(a, b)
    } else {
        calculate(b, a)
    }
}

fun calculate(a: Int, b: Int): Int {
    if (a == 0) return 0
    if (a == 1) return b
    val res = calculate(a / 2, b)

    return if (a % 2 == 0) {
        res + res
    } else {
        res + res + b
    }
}