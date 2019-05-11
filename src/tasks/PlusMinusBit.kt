package tasks

fun main() {
    println(759 - 674)
    println(minusBit(759, 674))
    println(759 + 674)
    println(plusBin(759, 674))
}

fun minusBit(a: Int, b: Int): Int {
    return a xor b
}

fun plusBin(a: Int, b: Int): Int{
    if (b == 0) return a
    val dif = a xor b
    val nb = (a and b) shl 1
    return plusBin(dif, nb)
}