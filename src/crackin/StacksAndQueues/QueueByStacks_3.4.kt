package crackin.StacksAndQueues

import Stack


//3.4 Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.

fun main() {
    val queue = QueueByStacks<Int>()
    queue.add(1)
    queue.add(2)
    queue.add(3)
    queue.add(4)
    queue.add(5)
    queue.add(6)
    queue.show()
    queue.remove()
    queue.remove()
    queue.show()

}

class QueueByStacks<T> {

    private val stack = Stack<T>()
    private val reverseStack = Stack<T>()

    fun add(value: T) {
        stack.push(value)
    }

    fun remove(): T {
        shiftStack()
        return reverseStack.pop()
    }

    private fun shiftStack() {
        if (reverseStack.isEmpty()) {
            while (stack.isNotEmpty()) {
                reverseStack.push(stack.pop())
            }
        }
    }

    fun show() {
        stack.show()
        reverseStack.show()
    }
}