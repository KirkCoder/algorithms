package crackin.LinkList

import SingleLinkedList
import generateSingleLinkedList

// Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.

fun main() {
    val list = generateSingleLinkedList(length = 15)
    list.show()
    println(getKthLastElement(list, 5))
}

private fun getKthLastElement(list: SingleLinkedList<Int>, k: Int): Int {
    val fastIterator = list.iterator()
    val targetIterator = list.iterator()
    for (i in 0 until k) {
        if (fastIterator.hasNext()) {
            fastIterator.next()
        } else {
            throw NoSuchElementException()
        }
    }
    while (fastIterator.hasNext()) {
        fastIterator.next()
        targetIterator.next()
    }

    return targetIterator.next()
}
