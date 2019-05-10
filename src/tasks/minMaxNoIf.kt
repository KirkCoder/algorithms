package tasks

fun main() {
    println(findMin(Int.MAX_VALUE, -7))
    println(findMinSmart(Int.MAX_VALUE, -7))
}

fun findMinSmart(a: Int, b: Int): Int {
    val ak = showSign(a)
    val bk = showSign(b)
    val ck = showSign(a - b)

    val isNotEqualSign = ak xor bk

    val k = ak * isNotEqualSign + ck * (isNotEqualSign xor 1)

    val r = k xor 1

    return a * r + b * k
}

private fun findMin(a: Int, b: Int): Int {
    val k = showSign(a - b)
    val r = k xor 1

    return a * r + b * k
}

// if > 0 return 1 else return 0
private fun showSign(x: Int): Int {
    return (x ushr 31) xor 1
}