package crackin.LinkList

import SingleLinkedList

fun main() {
    val list = SingleLinkedList<Int>()
    list.add(3)
    list.add(5)
    list.add(8)
    list.add(5)
    list.add(10)
    list.add(2)
    list.add(1)
    partitionListOnPlace(list, 5)
    list.show()
}

fun partitionListOnPlace(list: SingleLinkedList<Int>, p: Int) {
    var less = list.start!!
    var more = list.start!!

    var node = list.start

    while (node != null) {
        val next = node.next
        if (node.value < p) {
            node.next = less
            less = node
            node = next
        } else {
            more.next = node
            more = node
            node = next
        }
    }
    more.next = null
    list.start = less
}

fun partitionList(list: SingleLinkedList<Int>, p: Int) {
    val moreOrEquals = SingleLinkedList<Int>()
    if (list.start == null) return
    while (list.start!!.value >= p) {
        moreOrEquals.add(list.start!!.value)
        list.start = list.start!!.next
        if (list.start == null) {
            list.addAll(moreOrEquals)
        }
    }


    var current = list.start
    var next = list.start?.next

    while (next != null) {
        if (next.value < p) {
            current!!.next = next
            current = current.next
        } else {
            moreOrEquals.add(next.value)
        }
        next = next.next
    }

    list.addAll(moreOrEquals)
}