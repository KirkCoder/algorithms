package crackin.LinkList

import SingleLinkedList

//Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.
//DEFINI TION
//Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so as to make a loop in the linked list.
//EXAMPLE
//Input: A -) B -) C -) 0 -) E -) C[thesameCasearlierl Output: C

fun main() {
    val list = SingleLinkedList<Char>()
    val node = SingleLinkedList.Node('C')
    list.add(SingleLinkedList.Node('A'))
    list.add(SingleLinkedList.Node('B'))
    list.add(node)
    list.add(SingleLinkedList.Node('h'))
    list.add(SingleLinkedList.Node('r'))
    list.add(SingleLinkedList.Node('h'))
    list.add(SingleLinkedList.Node('y'))
    list.add(SingleLinkedList.Node('O'))
    list.add(SingleLinkedList.Node('E'))
    list.add(node)
    println(findCircleStartInListSmart(list)?.value)
}

fun <T> findCircleStartInListSmart(list: SingleLinkedList<T>): SingleLinkedList.Node<T>? {
    val slow = list.nodesIterator()
    val fast = list.nodesIterator()
    var s: SingleLinkedList.Node<T>
    var f: SingleLinkedList.Node<T>
    if (slow.hasNext()) {
        s = slow.next()
        f = fast.next()
    } else {
        return null
    }

    var firstStep = true

    while (s !== f || firstStep) {
        firstStep = false
        s = slow.next()
        if (!fast.hasNext()) return null
        fast.next()
        if (!fast.hasNext()) return null
        f = fast.next()
    }

    val target = list.nodesIterator()
    var t = target.next()
    while (t !== s) {
        s = slow.next()
        t = target.next()
    }

    return t
}

fun <T> findCircleStartInList(list: SingleLinkedList<T>): SingleLinkedList.Node<T>? {
    val tmpList = SingleLinkedList<SingleLinkedList.Node<T>>()
    val i = list.nodesIterator()
    while (i.hasNext()) {
        val node = i.next()
        if (isNodeContainsInList(tmpList, node)) {
            return node
        } else {
            tmpList.add(node)
        }
    }
    return null
}

private fun <T> isNodeContainsInList(
    list: SingleLinkedList<SingleLinkedList.Node<T>>,
    node: SingleLinkedList.Node<T>
): Boolean {
    val i = list.iterator()
    while (i.hasNext()) {
        val tmp = i.next()
        if (tmp === node) {
            return true
        }
    }
    return false
}
