import java.math.BigInteger
import java.util.*

fun <T> showArray(array: Array<T>) {
    for (i in array) {
        print("$i, ")
    }
    println()
}

fun generateArray(length: Int = 32) = Array(length) { (Math.random() * 99).toInt() }

fun generateNullableArray(length: Int = 32): Array<Int?> {
    val arr = arrayOfNulls<Int>(length)
    for (i in arr.withIndex()) {
        arr[i.index] = (Math.random() * 99).toInt()
    }
    return arr
}

fun findBigPrime(): BigInteger =
    BigInteger.probablePrime(4096, Random())