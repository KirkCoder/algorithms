import sort.arr
import java.math.BigInteger
import java.util.*

fun <T> showArray(array: Array<T>) {
    for (i in array.indices) {
        if (i < array.size - 1) {
            print("${array[i]}, ")
        } else {
            print("${array[i]}")
        }
    }
    println()
}

fun showArray(array: IntArray) {
    for (i in array.indices) {
        if (i < array.size - 1) {
            print("${array[i]}, ")
        } else {
            print("${array[i]}")
        }
    }
    println()
}

fun generateArray(length: Int = 32, maxItemValue: Int = 99) = Array(length) { (Math.random() * maxItemValue).toInt() }

fun generateNullableArray(length: Int = 32): Array<Int?> {
    val arr = arrayOfNulls<Int>(length)
    for (i in arr.withIndex()) {
        arr[i.index] = (Math.random() * 99).toInt()
    }
    return arr
}

fun findBigPrime(): BigInteger =
    BigInteger.probablePrime(4096, Random())