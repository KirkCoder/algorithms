package crackin.LinkList

import SingleLinkedList
import generateSingleLinkedList

fun main() {
    val list = generateSingleLinkedList()
    list.show()
    deleteMiddleNodeByPosition(list, 4)
    list.show()
    val secondList = generateSingleLinkedList(maxItemValue = 4)
    secondList.show()
    deleteMiddleNodeByValue(secondList, 0)
    secondList.show()
}

private fun deleteMiddleNodeByValue(list: SingleLinkedList<Int>, value: Int) {
    var element = list.start
    while (element?.value == value) {
        element = element.next
    }
    list.start = element

    var prev = list.start
    element = prev?.next

    while (element != null) {
        if (element.value == value) {
            element = element.next
            prev?.next = element
        } else {
            prev = element
            element = element.next
        }
    }
}

private fun deleteMiddleNodeByPosition(list: SingleLinkedList<Int>, position: Int) {
    val iterator = list.nodesIterator()
    var prev: SingleLinkedList.Node<Int>? = null
    for (i in 0 until position) {
        if (iterator.hasNext()) {
            prev = iterator.next()
        } else {
            throw NoSuchElementException()
        }
    }
    if (iterator.hasNext() && prev != null) {
        val target = iterator.next()
        prev.next = target.next
    } else {
        throw NoSuchElementException()
    }
}