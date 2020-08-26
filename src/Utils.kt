import java.math.BigInteger
import java.util.*
import kotlin.NoSuchElementException

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

@JvmOverloads
fun generateArray(length: Int = 32, maxItemValue: Int = 99) = Array(length) { generateRandomInt(maxItemValue) }

@JvmOverloads
fun generateNullableArray(length: Int = 32, maxItemValue: Int = 99): Array<Int?> {
    val arr = arrayOfNulls<Int>(length)
    for (i in arr.withIndex()) {
        arr[i.index] = generateRandomInt(maxItemValue)
    }
    return arr
}

fun generateRandomInt(maxItemValue: Int) = (Math.random() * maxItemValue).toInt()

fun generateSingleLinkedList(length: Int = 10, maxItemValue: Int = 99): SingleLinkedList<Int> {
    return SingleLinkedList<Int>().apply {
        for (i in 0..length) {
            add(generateRandomInt(maxItemValue))
        }
    }
}

fun generateLinkedList(length: Int = 10, maxItemValue: Int = 99): LinkedList<Int> {
    return LinkedList<Int>().apply {
        for (i in 0..length) {
            add(generateRandomInt(maxItemValue))
        }
    }
}

fun findBigPrime(): BigInteger =
    BigInteger.probablePrime(4096, Random())

fun showMatrix(arr: Array<Array<Int>>) {
    for (ints in arr) {
        for (int in ints) {
            print("$int  ")
            if (int < 10) print(" ")
        }
        println()
    }
    println()
}

class SingleLinkedList<T> : Iterable<T> {

    var start: Node<T>? = null

    val length: Int
        get() {
            val i = iterator()
            var count = 0
            while (i.hasNext()) {
                count++
                i.next()
            }
            return count
        }

    val last: Node<T>?
        get() {
            val i = nodesIterator()
            var last: Node<T>? = null
            while (i.hasNext()) {
                last = i.next()
            }
            return last
        }

    fun add(value: T) {
        add(Node(value))
    }

    fun add(node: Node<T>) {
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

    fun removeLast(): T {
        val i = nodesIterator()
        if (!i.hasNext()) throw NoSuchElementException()
        var prev: Node<T>? = i.next()
        var next = prev?.next
        if (next == null) {
            val tmp = prev!!.value
            start = null
            return tmp
        }
        while (i.hasNext()) {
            prev = next
            next = i.next()
        }
        val tmp = next!!.value
        prev?.next = null
        return tmp
    }

    fun addAll(list: SingleLinkedList<T>) {
        if (list.isEmpty()) return
        val i = list.nodesIterator()
        if (start == null) {
            start = i.next()
        }
        var currentLast = last ?: start!!
        while (i.hasNext()) {
            val next = i.next()
            currentLast.next = next
            currentLast = next
        }
    }

    override fun iterator(): Iterator<T> {
        return SingleLinkedListIterator(start)
    }

    fun nodesIterator(): Iterator<Node<T>> {
        return SingleLinkedListNodeIterator(start)
    }

    fun show() {
        showIterable(iterator())
    }

    fun isEmpty() = start == null

    class Node<T>(
        val value: T,
        var next: Node<T>? = null
    ) {
        fun changeNext(value: T) {
            next = Node(value)
        }
    }

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

    private class SingleLinkedListNodeIterator<T>(
        private var node: Node<T>?
    ) : Iterator<Node<T>> {

        override fun hasNext() = node != null

        override fun next(): Node<T> {
            val current = node ?: throw NoSuchElementException()
            node = current.next
            return current
        }

    }

}

class Stack<T> {
    var head: Node<T>? = null
    var size: Int = 0
        private set

    fun push(value: T) {
        val node = Node(value)
        if (head != null) {
            node.prev = head
        }
        head = node
        size++
    }

    fun pop(): T {
        if (isNotEmpty()) {
            val tmp = head!!.value
            head = head!!.prev
            size--
            return tmp
        } else {
            throw NoSuchElementException()
        }
    }

    fun peek(): T {
        if (isNotEmpty()) {
            return head!!.value
        } else {
            throw NoSuchElementException()
        }
    }

    fun isEmpty() = head == null
    fun isNotEmpty() = head != null

    fun show() {
        var node = head
        val list = mutableListOf<T>()
        while (node != null) {
            list.add(node.value)
            node = node.prev
        }
        list.reverse()
        showIterable(list.iterator())
    }

    class Node<T>(val value: T, var prev: Node<T>? = null)
}

class Queue<T> {
    var start: Node<T>? = null
    private set
    var end: Node<T>? = null
    private set


    fun add(value: T) {
        val node = Node(value)
        if (start == null) {
            start = node
            end = node
        } else {
            end!!.next = node
            end = node
        }
    }

    fun remove(): T {
        val tmp = start ?: throw NoSuchElementException()
        start = tmp.next
        if (start == null) {
            end = null
        }
        return tmp.value
    }

    fun peek(): T {
        return start?.value ?: throw NoSuchElementException()
    }

    fun show() {
        var next = start
        val list = mutableListOf<T>()
        while (next != null) {
            list.add(next.value)
            next = next.next
        }
        showIterable(list.iterator())
    }

    class Node<T>(val value: T, var next: Node<T>? = null)
}