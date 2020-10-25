import crackin.Graphs.getNodesList
import tasks.Heap
import java.lang.StringBuilder
import java.math.BigInteger
import java.util.*
import kotlin.Comparator
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
fun generateUniqueSortedArray(maxLength: Int = 32, maxItemValue: Int = 99): Array<Int> {
    val array = generateArray(maxLength, maxItemValue)
    val set = array.toSet()
    val result = set.toTypedArray()
    result.sort()
    return result
}

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


data class GraphNode<T>(
    val value: T,
    var visited: Boolean = false,
    var isBlack: Boolean = false,
    var adjacencs: MutableList<GraphNode<T>> = mutableListOf(),
    var left: GraphNode<T>? = null,
    var right: GraphNode<T>? = null,
    var parent: GraphNode<T>? = null
) {

    override fun hashCode(): Int {
        return value.toString().toInt()
    }

    override fun toString(): String {
        return "$value, adjacencs: ${adjacencs.map { it.value }.joinToString(", ")}. "
    }

    fun showWithAdjacencs() {
        print("(Node: $value, left: ${left?.value}, right: ${right?.value}, isBlack: ${isBlack}), ")
    }
}

fun <T> showBinaryTree(head: GraphNode<T>) {
    val list = getNodesList(head)
    for (nodes in list) {
        println(nodes.map { it.value }.joinToString(", "))
    }
    for (nodes in list) {
        println(nodes.forEach { it.showWithAdjacencs() })
    }
}

fun getDirectGraph(): List<GraphNode<Int>> {
    val graph = mutableListOf<GraphNode<Int>>()

    val zero = GraphNode(0)
    val one = GraphNode(1)
    val two = GraphNode(2)
    val three = GraphNode(3)
    val four = GraphNode(4)
    val five = GraphNode(5)
    val six = GraphNode(6)
    val seven = GraphNode(7)
    two.adjacencs = mutableListOf(three)
    three.adjacencs = mutableListOf(four)
    four.adjacencs = mutableListOf(two)
    five.adjacencs = mutableListOf(seven)
    six.adjacencs = mutableListOf(five, one)
    seven.adjacencs = mutableListOf(zero, two)

    graph.add(zero)
    graph.add(one)
    graph.add(two)
    graph.add(three)
    graph.add(four)
    graph.add(five)
    graph.add(six)
    graph.add(seven)

    return graph
}

fun getNotOrientedGraph(): List<GraphNode<Int>> {
    val graph = mutableListOf<GraphNode<Int>>()

    val zero = GraphNode(0)
    val one = GraphNode(1)
    val two = GraphNode(2)
    val three = GraphNode(3)
    val four = GraphNode(4)
    val five = GraphNode(5)
    val six = GraphNode(6)
    val seven = GraphNode(7)
    zero.adjacencs = mutableListOf(seven)
    one.adjacencs = mutableListOf(six)
    two.adjacencs = mutableListOf(three, four, seven)
    three.adjacencs = mutableListOf(four, two)
    four.adjacencs = mutableListOf(two, three)
    five.adjacencs = mutableListOf(seven, six)
    six.adjacencs = mutableListOf(five, one)
    seven.adjacencs = mutableListOf(zero, two, five)

    graph.add(zero)
    graph.add(one)
    graph.add(two)
    graph.add(three)
    graph.add(four)
    graph.add(five)
    graph.add(six)
    graph.add(seven)

    return graph
}

fun getNotBalancedBinaryTree(): GraphNode<Int> {
    val zero = GraphNode(0)
    val one = GraphNode(1)
    val two = GraphNode(2)
    val three = GraphNode(3)
    val four = GraphNode(4)
    val five = GraphNode(5)
    val six = GraphNode(6)
    val seven = GraphNode(7)
    val eight = GraphNode(8)

    zero.left = one
    zero.right = two

    one.left = three
    one.right = four

    two.left = five
    two.right = six

    three.left = seven

    seven.right = eight
    return zero
}

fun getBinarySearchWikiTree(): Pair<MutableMap<Int, GraphNode<Int>>, GraphNode<Int>> {
    val one = GraphNode(1)
    val three = GraphNode(3)
    val four = GraphNode(4)
    val six = GraphNode(6)
    val seven = GraphNode(7)
    val eight = GraphNode(8)
    val ten = GraphNode(10)
    val thirteen = GraphNode(13)
    val fourteen = GraphNode(14)

    val nodes = mutableMapOf<Int, GraphNode<Int>>()
    nodes[1] = one
    nodes[3] = three
    nodes[4] = four
    nodes[6] = six
    nodes[7] = seven
    nodes[8] = eight
    nodes[10] = ten
    nodes[13] = thirteen
    nodes[14] = fourteen

    one.parent = three
    three.left = one
    three.right = six
    three.parent = eight
    six.left = four
    six.right = seven
    six.parent = three
    four.parent = six
    seven.parent = six
    eight.left = three
    eight.right = ten
    ten.parent = eight
    ten.right = fourteen
    fourteen.parent = ten
    fourteen.left = thirteen
    thirteen.parent = fourteen
    return Pair(nodes, eight)
}

fun <T> List<T>.toLinkedList(): LinkedList<T> {
    val list = LinkedList<T>()
    for (element in this) {
        list.add(element)
    }
    return list
}

fun Int.toBinaryString(): String {
    var tmp = this
    val sb = StringBuilder()
    while (tmp != 0) {
        if (tmp % 2 == 0) {
            sb.append(0)
        } else {
            sb.append(1)
        }
        tmp /= 2
    }
    return sb.toString().reversed()
}
