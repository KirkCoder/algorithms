package crackin.LinkList

import SingleLinkedList
import Stack

//Palindrome: Implement a function to check if a linked list is a palindrome.

fun main() {
    val list = SingleLinkedList<Char>()
    list.add('A')
    list.add('B')
    list.add('C')
    list.add('B')
    list.add('A')
    println(isPalindromInStack(list))
}

fun <T> isPalindrom(list: SingleLinkedList<T>): Boolean {
    val copy = copy(list)
    revert(list)
    val i = list.iterator()
    val j = copy.iterator()
    while (i.hasNext()) {
        if (i.next() != j.next()) {
            return false
        }
    }
    return true
}

private fun <T> copy(list: SingleLinkedList<T>): SingleLinkedList<T> {
    val newList = SingleLinkedList<T>()
    val i = list.iterator()
    while (i.hasNext()) {
        newList.add(i.next())
    }
    return newList
}

private fun <T> revert(list: SingleLinkedList<T>) {
    var prev: SingleLinkedList.Node<T>? = null
    var current = list.start
    while (current != null) {
        val tmp = current.next
        current.next = prev
        prev = current
        current = tmp
    }
    list.start = prev
}

fun <T> isPalindromInStack(list: SingleLinkedList<T>): Boolean {
    var slow = list.start
    var fast = list.start
    val stack = Stack<T>()

    while (fast != null) {
        stack.push(slow!!.value)
        slow = slow.next
        fast = fast.next?.next
    }

    if (list.length % 2 > 0) {
        stack.pop()
    }

    while (stack.isNotEmpty()) {
        if (stack.pop() != slow!!.value) {
            return false
        }
        slow = slow.next
    }

    return true
}