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

class SingleLinkedList<T> : Iterable<T> {

    private var start: Node<T>? = null

    fun add(value: T) {
        val node = Node(value)
        val tmpStart = start
        if (tmpStart == null) {
            start = node
        } else {
            var current = tmpStart
            var next = tmpStart.next
            while (next != null) {
                current = next
                next = current.next
            }
            current!!.next = node
        }
    }

    override fun iterator(): Iterator<T> {
        return SingleLinkedListIterator(start)
    }

    private class Node<T>(
        val value: T,
        var next: Node<T>? = null
    )

    private class SingleLinkedListIterator<T>(
        private var node: Node<T>?
    ) : Iterator<T> {

        override fun hasNext() = node != null

        override fun next(): T {
            val current = node ?: throw NoSuchElementException()
            node = current.next
            return current.value
        }

    }

}