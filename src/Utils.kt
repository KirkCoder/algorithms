import java.math.BigInteger
import java.util.*

fun <T> showArray(array: Array<T>) {
    showIterable(array.iterator())
}

fun <T> showList(list: List<T>) {
    showIterable(list.iterator())
}

fun showArray(array: IntArray) {
    showIterable(array.iterator())
}

fun <T> showIterable(iterator: Iterator<T>) {
    while (iterator.hasNext()) {
        print("${iterator.next()}, ")
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