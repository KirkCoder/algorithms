package crackin.Graphs

import GraphNode
import getBinarySearchWikiTree
import showIterable
import java.util.*

fun main() {
    val graph = getBinarySearchWikiTree()
//    graph.first[1]!!.right = graph.first[6]
    val res = getTopologicalSort(graph.second)
    showIterable(res.map { it.value }.iterator())
}

fun <T> getTopologicalSort(head: GraphNode<T>): List<GraphNode<T>> {
    val sorted = LinkedList<GraphNode<T>>()
    val isFound = getTopologicalSort(head, sorted)
    return if (isFound) {
        sorted.reversed()
    } else {
        emptyList()
    }
}

fun <T> getTopologicalSort(node: GraphNode<T>, sorted: LinkedList<GraphNode<T>>): Boolean {
    val left = node.left
    val right = node.right
    if ((left != null && left.visited) || (right != null && right.visited)) return false
    if (left != null) {
        val isFound = getTopologicalSort(left, sorted)
        if (!isFound) return false
    }
    if (right != null) {
        val isFound = getTopologicalSort(right, sorted)
        if (!isFound) return false
    }

    if ((left == null || left.visited) && (right == null || right.visited)) {
        node.visited = true
        sorted.add(node)
    }

    return true
}