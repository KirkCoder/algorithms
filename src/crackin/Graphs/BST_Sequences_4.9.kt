package crackin.Graphs

import GraphNode
import getBinarySearchWikiTree
import showIterable
import showList
import java.util.*

//BST Sequences: A binary search tree was created by traversing through an array from left to right and inserting each element. Given a binary search tree with distinct elements, print all possible arrays that could have led to this tree.
//   2
// 1   3
//    Output: {2, 1, 3}, {2, 3, 1}


fun main() {
    val tree = getBinarySearchWikiTree().second
    val two = GraphNode(2)
    val one = GraphNode(1)
    val three = GraphNode(3)
    two.left = one
    two.right = three
    val result = getBstSequences(tree)
    println("")
}

private fun <T> getBstSequences(node: GraphNode<T>?): MutableList<LinkedList<T>> {
    val result = mutableListOf<LinkedList<T>>()
    if (node == null) {
        result.add(LinkedList())
        return result
    }

    val leftSeq = getBstSequences(node.left)
    val rightSeq = getBstSequences(node.right)

    val prefix = LinkedList<T>()
    prefix.add(node.value)

    for (left in leftSeq) {
        for (right in rightSeq) {
            val res = LinkedList<LinkedList<T>>()
            mix(left, right, res, prefix)
            result.addAll(res)
        }
    }
    return result
}

private fun <T> mix(
    left: LinkedList<T>,
    right: LinkedList<T>,
    result: LinkedList<LinkedList<T>>,
    prefix: LinkedList<T>
) {
    if (left.isEmpty() || right.isEmpty()) {
        val res = LinkedList<T>()
        res.addAll(prefix)
        res.addAll(left)
        res.addAll(right)
        result.add(res)
        return
    }

    val first = left.removeFirst()
    prefix.add(first)
    mix(left, right, result, prefix)
    prefix.removeLast()
    left.addFirst(first)

    val second = right.removeFirst()
    prefix.add(second)
    mix(left, right, result, prefix)
    prefix.removeLast()
    right.addFirst(second)
}