package crackin.StacksAndQueues

//Stack Min: How would you design a stack which, in addition to push and pop, has a function min
//which returns the minimum element? Push, pop and min should all operate in 0(1) time.
//

fun main() {
    val stack = StackWithMin<Int>()
    stack.push(3)
    stack.push(30)
    stack.push(1)
    stack.push(8)
    stack.push(834)
    stack.push(0)
    println(stack.min())
    stack.pop()
    stack.pop()
    println(stack.min())
    stack.pop()
    stack.pop()
    println(stack.min())
}

class StackWithMin<T : Comparable<T>>() {

    private var peek: Node<T>? = null
    private var min: Node<T>? = null

    fun push(value: T) {
        val tmp = peek
        val node = Node(value)
        if (tmp == null) {
            peek = node
            min = node
        } else {
            node.prev = tmp
            if (min!!.value > value) {
                node.prevMin = min
                min = node
            }
            peek = node
        }
    }

    fun pop(): T {
        val tmp = peek
        if (tmp == null) {
            throw NoSuchElementException()
        } else {
            if (tmp === min) {
                min = min!!.prevMin
            }
            peek = tmp.prev
        }
        return tmp.value
    }

    fun min(): T {
        if (min == null) throw NoSuchElementException()
        return min!!.value
    }

    class Node<T>(val value: T, var prev: Node<T>? = null, var prevMin: Node<T>? = null)
}