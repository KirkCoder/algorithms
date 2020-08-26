package crackin.StacksAndQueues

import showIterable

//Three in One: Describe how you could use a single array to implement three stacks.

fun main() {
    val stack = ThreeStacks<Int>()

    for (i in 0 until 3) {
        stack.pushFirst(i)
    }
    for (i in 3 until 6) {
        stack.pushSecond(i)
    }
    for (i in 6 until 9) {
        stack.pushThird(i)
    }

    stack.show()
    println("run pop")
    for (i in 0 until 3) {
        stack.popFirst()
        stack.show()
        stack.popSecond()
        stack.show()
        stack.popThird()
        stack.show()
    }

}

class ThreeStacks<T>() {
    private val array = Array<Node<T>>(ARRAY_SIZE) { Node.Empty() }


    fun pushFirst(value: T) {
        push(Stack.FIRST, value)
    }

    fun pushSecond(value: T) {
        push(Stack.SECOND, value)
    }

    fun pushThird(value: T) {
        push(Stack.THIRD, value)
    }

    fun popFirst(): T {
        return pop(Stack.FIRST)
    }

    fun popSecond(): T {
        return pop(Stack.SECOND)
    }

    fun popThird(): T {
        return pop(Stack.THIRD)
    }

    fun show() {
        showIterable(array.map {
            if (it is Node.Value) {
                it.value
            } else {
                -1
            }
        }.iterator())
    }

    private fun push(stack: Stack, value: T) {
        for (i in stack.startPosition..stack.endPosition) {
            if (array[i] is Node.Empty) {
                array[i] = Node.Value(value)
                return
            }
        }
        throw StackFullException()
    }

    private fun pop(stack: Stack): T {
        for (i in stack.endPosition downTo stack.startPosition) {
            if (array[i] !is Node.Empty) {
                val tmp = (array[i] as Node.Value).value
                array[i] = Node.Empty()
                return tmp
            }
        }
        throw NoSuchElementException()
    }


    private enum class Stack(val startPosition: Int, val endPosition: Int) {
        FIRST(0, 2), SECOND(3, 5), THIRD(6, 8)
    }

    private sealed class Node<T> {
        data class Value<T>(val value: T) : Node<T>()
        class Empty<T> : Node<T>()
    }

    companion object {
        private const val ARRAY_SIZE = 9
    }

    class StackFullException : Throwable()
}