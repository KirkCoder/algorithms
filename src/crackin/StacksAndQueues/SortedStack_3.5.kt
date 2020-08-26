package crackin.StacksAndQueues

import Stack
import generateArray
import showArray

//3.5 Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use an additional temporary stack, but you may not copy the elements into any other data structure (such as an array).The stack supports the following operations: push, pop, peek, and isEmpty.


fun main() {
    val array = generateArray()
    showArray(array)
    println("")
//    val stack = SortedStack<Int>()
    val stack = Stack<Int>()
    for (i in array) {
        stack.push(i)
    }
//    stack.show()
//    stack.pop()
//    stack.pop()
//    stack.pop()
//    stack.pop()
//    stack.show()
//    sortStackWithOnlyOneAdditional(stack.size(), stack, Stack<Int>())
//    sortStackWithOneAdditional(stack)
//    stack.show()


    val res = sort(stack)
    res.show()
}

private fun <T : Comparable<T>> sort(stack: Stack<T>): Stack<T> {
    if (stack.size <= 1) return stack

    val left = Stack<T>()
    val right = Stack<T>()
    for (i in 0 until stack.size) {
        if (i % 2 == 0) {
            left.push(stack.pop())
        } else {
            right.push(stack.pop())
        }
    }
    return merge(sort(left), sort(right))
}

private fun <T : Comparable<T>> merge(left: Stack<T>, right: Stack<T>): Stack<T> {
    val result = Stack<T>()
    while (left.isNotEmpty() && right.isNotEmpty()) {
        if (left.peek() >= right.peek()) {
            result.push(left.pop())
        } else {
            result.push(right.pop())
        }
    }

    while (left.isNotEmpty()) {
        result.push(left.pop())
    }

    while (right.isNotEmpty()) {
        result.push(right.pop())
    }

    val reverted = Stack<T>()

    while (result.isNotEmpty()) {
        reverted.push(result.pop())
    }
    return reverted
}

fun <T : Comparable<T>> sortStackWithOneAdditional(stack: Stack<T>) {
    val tmpStack = Stack<T>()

    while (stack.isNotEmpty()) {
        val next = stack.pop()

        while (tmpStack.isNotEmpty() && tmpStack.peek() > next) {
            stack.push(tmpStack.pop())
        }

        tmpStack.push(next)
    }

    while (tmpStack.isNotEmpty()) {
        stack.push(tmpStack.pop())
    }
}

private fun <T : Comparable<T>> sortStackWithOnlyOneAdditional(deepSize: Int, root: Stack<T>, tmp: Stack<T>): Stack<T> {
    var tmpSize = deepSize
    var maxValue = root.pop()
    while (root.isNotEmpty() && tmpSize != 0) {
        val next = root.pop()
        if (next > maxValue) {
            tmp.push(maxValue)
            maxValue = next
        } else {
            tmp.push(next)
        }
        tmpSize--
    }
    root.push(maxValue)

    while (tmp.isNotEmpty()) {
        root.push(tmp.pop())
    }

    return if (root.peek() == maxValue) {
        root
    } else {
        sortStackWithOnlyOneAdditional(deepSize - 1, root, tmp)
    }
}


class SortedStack<T : Comparable<T>> {

    private var sortedStack: Stack<T> = Stack()

    fun push(value: T) {
        if (sortedStack.isEmpty() || value <= sortedStack.peek()) {
            sortedStack.push(value)
        } else {
            val tmpStack = Stack<T>()
            var element = sortedStack.pop()
            while (sortedStack.isNotEmpty() && element < value) {
                tmpStack.push(element)
                element = sortedStack.pop()
            }

            if (element < value) {
                sortedStack.push(value)
                sortedStack.push(element)
            } else {
                sortedStack.push(element)
                sortedStack.push(value)
            }

            while (tmpStack.isNotEmpty()) {
                sortedStack.push(tmpStack.pop())
            }
        }
    }

    fun peek(): T {
        return sortedStack.peek()
    }

    fun pop(): T {
        return sortedStack.pop()
    }

    fun isEmpty() = sortedStack.isEmpty()

    fun show() = sortedStack.show()
}