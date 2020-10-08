package crackin.Graphs

import GraphNode
import getBinarySearchWikiTree

fun main() {
    val pair = getBinarySearchWikiTree()
    println(findPrevNode(pair.first[1]!!)?.value)
}

fun <T : Comparable<T>> findPrevNode(node: GraphNode<T>): GraphNode<T>? {
    val right = node.right
    if (right != null) {
        return findLeftSuccessor(right)
    }

    var prev = node.parent
    var tmp = node
    if (prev == null) return null

    while (prev != null) {
        if (tmp == prev.left) {
            return prev
        }
        tmp = prev
        prev = tmp.parent
    }

    return null
}

private fun <T> findLeftSuccessor(node: GraphNode<T>): GraphNode<T> {
    var next = node
    var left = next.left
    while (left != null) {
        next = left
        left = next.left
    }

    return next
}