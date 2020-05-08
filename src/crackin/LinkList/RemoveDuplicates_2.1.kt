package crackin.LinkList

import SingleLinkedList
import showIterable

//2.1 Remove Dups: Write code to remove duplicates from an unsorted linked list. FOLLOW UP
//How would you solve this problem if a temporary buffer is not allowed?

fun main() {
    val list = SingleLinkedList<Int>()
    list.add(1)
    list.add(4)
    list.add(75)
    list.add(45)
    list.add(8)
    list.add(4)
    list.add(95)
    withBuffer(list)
    showIterable(list.iterator())
}

private fun withBuffer(list: SingleLinkedList<Int>) {
    val set = HashSet<Int>()
    var prev: SingleLinkedList.Node<Int>? = null
    for (node in list.nodesIterator()) {
        if (prev != null) {
            prev.next = node
        }
        val containsValue = set.contains(node.value)
        if (!containsValue) {
            set.add(node.value)
            prev = node
        }
    }
}

private fun withOutBuffer(list: SingleLinkedList<Int>) {
    var slow = list.start
    while (slow?.next != null) {
        slow = slow.next
        var prev = slow
        var current = slow?.next
        while (current != null) {
            prev!!.next = current
            if (slow!!.value != current.value) {
                prev = current
                current = current.next
            } else {
                current = current.next
            }
        }
    }
}