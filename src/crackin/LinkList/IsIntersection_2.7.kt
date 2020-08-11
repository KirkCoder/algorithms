package crackin.LinkList

import SingleLinkedList

//Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the inter- secting node. Note that the intersection is defined based on reference, not value. That is, if the kth node of the first linked list is the exact same node (by reference) as the jth node of the second linked list, then they are intersecting.

fun main() {

    val intersection = SingleLinkedList.Node(4)

    val first = generateList(intersection, 2)
    val second = generateList(intersection, 6)
    println(isIntersectingNorm(first, second))
}

private fun generateList(intersection: SingleLinkedList.Node<Int>, intersectionPosition: Int): SingleLinkedList<Int> {
    val first = SingleLinkedList<Int>()
    first.start = SingleLinkedList.Node(0)
    var next = first.start!!
    for (i in 1..7) {
        if (i == intersectionPosition) {
            next.next = intersection
            next = next.next!!
        }
        next.next = SingleLinkedList.Node(i)
        next = next.next!!
    }
    return first
}

//O(M*N)
fun <T> isIntersectingNorm(first: SingleLinkedList<T>, second: SingleLinkedList<T>): SingleLinkedList.Node<T>? {
    var firstLast = first.start?.next
    var firstPrev = first.start
    var firstSize = 1
    while (firstLast != null) {
        firstSize++
        firstPrev = firstLast
        firstLast = firstLast.next
    }

    var secondLast = second.start?.next
    var secondPrev = second.start
    var secondSize = 1
    while (secondLast != null) {
        secondSize++
        secondPrev = secondLast
        secondLast = secondLast.next
    }

    if (firstPrev === secondPrev) {
        val f = first.nodesIterator()
        val s = second.nodesIterator()
        moveItarator(firstSize, secondSize, f)
        moveItarator(secondSize, firstSize, s)
        while (f.hasNext()) {
            val firstValue = f.next()
            val secondValue = s.next()
            if (firstValue === secondValue) {
                return firstValue
            }
        }
    } else {
        return null
    }
    return null
}

private fun <T> moveItarator(
    firstSize: Int,
    secondSize: Int,
    f: Iterator<SingleLinkedList.Node<T>>
) {
    val diff = firstSize - secondSize
    if (firstSize > secondSize) {
        for (i in diff downTo 1) {
            f.next()
        }
    }
}

//O(M*N)
fun <T> isIntersectingStupid(first: SingleLinkedList<T>, second: SingleLinkedList<T>): SingleLinkedList.Node<T>? {
    var current = first.start
    while (current != null) {
        val i = second.nodesIterator()
        while (i.hasNext()) {
            if (current === i.next()) {
                return current
            }
        }
        current = current.next
    }
    return null
}

//O(M + N)
fun <T> isIntersectingSmart(first: SingleLinkedList<T>, second: SingleLinkedList<T>): SingleLinkedList.Node<T>? {
    val map = mutableMapOf<Int, SingleLinkedList.Node<T>>()
    val i = first.nodesIterator()
    while (i.hasNext()) {
        val value = i.next()
        map[getReference(value)] = value
    }

    val j = second.nodesIterator()
    while (j.hasNext()) {
        val value = j.next()
        if (map[getReference(value)] != null) {
            return map[getReference(value)]
        }
    }
    return null
}

private fun getReference(any: Any): Int {
    return System.identityHashCode(any)
}