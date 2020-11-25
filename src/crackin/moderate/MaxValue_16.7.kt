package crackin.moderate

//Number Max: Write a method that finds the maximum of two numbers. You should not use if-else or any other comparison operator.

fun main() {
    println(maxValue(Int.MAX_VALUE, -7))
}

fun maxValue(first: Int, second: Int): Int {
    val f = sign(first)
    val s = sign(second)
    val k = sign(first - second)

    val fs = f xor s
    val fsf = flip(fs)

    return (first * k + second * flip(k)) * fsf + (first * f + second * s) * fs
}


fun sign(a: Int): Int {
    return flip((a ushr 31) and 1)
}

fun flip(a: Int): Int {
    return a xor 1
}