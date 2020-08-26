package crackin.StacksAndQueues

import SingleLinkedList
import Stack

//Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real life, we would likely start a new stack when the previous stack exceeds some threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be composed of several stacks and should create a new stack once the previous one exceeds capacity. SetOfStacks. push () and SetOfStacks. pop () should behave identically to a single stack (that is, pop ( ) should return the same values as it would if there were just a single stack).
//FOLLOW UP
//ImplementafunctionpopAt(int index)whichperformsapopoperationonaspecificsub-stack. Hints: #64, #87

fun main() {
    val set = SetOfStackSmart<Int>(4)
    set.push(1)
    set.push(2)
    set.push(3)
    set.push(4)
    set.show()
    set.push(5)
    set.push(6)
    set.push(7)
    set.push(8)
    set.push(9)
//    set.push(10)
    set.show()
//    println("poped: ${set.pop()}")
//    set.show()
    println("popedAt: ${set.popAt(0)}")
    set.show()
    println("popedAt: ${set.popAt(0)}")
    set.show()

    set.push(11)
    set.push(22)
    set.show()
}

class SetOfStackSmart<T>(private val maxCapacity: Int) {
    private val stacks = mutableListOf<Stack<T>>()

    fun push(value: T) {
        val stack = getLastNotFullStackStack()
        stack.push(value)
    }

    fun peek(): T {
        if (stacks.isEmpty()) throw NoSuchElementException()
        return stacks[stacks.size - 1].peek()
    }

    fun pop(): T {
        if (stacks.isEmpty()) throw NoSuchElementException()
        val stack = stacks[stacks.size - 1]
        val value = stack.pop()
        if (stack.isEmpty()) {
            stacks.removeAt(stacks.size - 1)
        }
        return value
    }

    fun popAt(stackIndex: Int): T {
        if (stacks.size <= stackIndex) throw NoSuchElementException()
        val stack = stacks[stackIndex]
        val value = stack.pop()
        if (stack.isEmpty()) {
            stacks.removeAt(stacks.size - 1)
        } else {
            shiftElements(stackIndex)
        }
        return value
    }

    private fun shiftElements(stackIndex: Int) {
        if (stacks.size - 1 > stackIndex) {
            var prevStack = stacks[stackIndex]
            for (i in stackIndex until stacks.size) {
                val tmpStack = Stack<T>()
                val stack = stacks[i]
                while (stack.isNotEmpty()) {
                    tmpStack.push(stack.pop())
                }
                while (prevStack.size < maxCapacity && tmpStack.isNotEmpty()) {
                    prevStack.push(tmpStack.pop())
                }
                while (tmpStack.isNotEmpty()) {
                    stack.push(tmpStack.pop())
                }
                prevStack = stack
            }
            if (stacks[stacks.size - 1].isEmpty()) {
                stacks.removeAt(stacks.size - 1)
            }
        }
    }

    private fun getLastNotFullStackStack(): Stack<T> {
        if (stacks.isNotEmpty() && stacks[stacks.size - 1].size < maxCapacity) {
            return stacks[stacks.size - 1]
        } else {
            return addNewStackInSet()
        }
    }

    private fun addNewStackInSet(): Stack<T> {
        val stack = Stack<T>()
        stacks.add(stack)
        return stack
    }

    private class Stack<T> {

        var head: Node<T>? = null

        var size = 0
            private set

        fun push(value: T) {
            val tmpHead = head
            val node = Node(value)
            size++
            if (tmpHead == null) {
                head = node
            } else {
                node.prev = tmpHead
                head = node
            }
        }

        fun peek(): T {
            return head?.value ?: throw NoSuchElementException()
        }

        fun pop(): T {
            val tmpHead = head ?: throw NoSuchElementException()
            size--
            head = tmpHead.prev
            return tmpHead.value
        }

        fun isEmpty() = head == null
        fun isNotEmpty() = head != null

        class Node<T>(val value: T, var prev: Node<T>? = null)
    }

    fun show() {
        val i = stacks.iterator()
        var j = 0
        while (i.hasNext()) {
            println("Stack #$j")
            var node = i.next().head
            while (node != null) {
                print("${node.value}, ")
                node = node.prev
            }
            j++
            println()
        }
        println("==================")
        println()
    }

}

class SetOfStacks<T>(private val maxCapacity: Int) {

    private var stacks = SingleLinkedList<Stack<T>>()

    fun push(value: T) {
        if (stacks.start == null) {
            stacks.add(Stack<T>())
        }
        val i = stacks.iterator()
        var positionFound = false
        while (i.hasNext()) {
            val stack = i.next()
            if (stack.size < maxCapacity) {
                stack.push(value)
                positionFound = true
                break
            }
        }
        if (!positionFound) {
            val stack = Stack<T>()
            stack.push(value)
            stacks.add(stack)
        }
    }

    fun pop(): T {
        val i = stacks.iterator()
        var stack: Stack<T>
        if (i.hasNext()) {
            stack = i.next()
        } else {
            throw NoSuchElementException()
        }
        while (i.hasNext()) {
            stack = i.next()
        }
        val tmp = stack.pop()
        if (stack.isEmpty()) {
            stacks.removeLast()
        }
        return tmp
    }

    fun popAt(index: Int): T {
        if (index < 0) throw NoSuchElementException()
        val stackNode = getStackNodeAt(index)
        val tmp = stackNode.value.pop()
        when {
            stacks.length == 1 -> {
                return tmp
            }
            stackNode.value.isEmpty() -> {
                stacks.removeLast()
                return tmp
            }
            stackNode.next == null -> {
                return tmp
            }
            else -> {
                var nextNode = stackNode.next
                stackNode.next = null
                val stack = Stack<T>()
                while (nextNode != null) {
                    val s = nextNode.value
                    var v: T? = s.pop()
                    while (v != null) {
                        stack.push(v)
                        v = try {
                            s.pop()
                        } catch (e: NoSuchElementException) {
                            null
                        }
                    }
                    while (stack.isNotEmpty()) {
                        push(stack.pop())
                    }
                    nextNode = nextNode.next
                }
                return tmp
            }
        }
    }

    private fun getStackNodeAt(index: Int): SingleLinkedList.Node<Stack<T>> {
        var stack: SingleLinkedList.Node<Stack<T>>? = null
        val i = stacks.nodesIterator()
        for (j in 0..index) {
            if (i.hasNext()) {
                stack = i.next()
            } else {
                break
            }
        }
        return stack ?: throw NoSuchElementException()
    }

    fun show() {
        val i = stacks.iterator()
        var j = 0
        while (i.hasNext()) {
            println("Stack #$j")
            var node = i.next().head
            while (node != null) {
                print("${node.value}, ")
                node = node.prev
            }
            j++
            println()
        }
        println("==================")
        println()
    }
}












