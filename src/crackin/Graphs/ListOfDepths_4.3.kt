package crackin.Graphs

import GraphNode
import generateUniqueSortedArray

fun main() {
    val head = getMinSearchTree(generateUniqueSortedArray())!!
    val first = getNodesList(head)
    val second = getNodesListNotRecurs(head)

    showGraphNodeList(first)
    println("===================")
    showGraphNodeList(second)
}

private fun showGraphNodeList(list: List<List<GraphNode<Int>>>) {
    for (nodes in list) {
        println(nodes.map { it.value }.joinToString(", "))
    }
}

fun <T> getNodesList(node: GraphNode<T>): List<List<GraphNode<T>>> {
    return getNodesListRecurs(mutableListOf(listOf(node)))
}

fun <T> getNodesListNotRecurs(head: GraphNode<T>): List<List<GraphNode<T>>> {
    val result = mutableListOf<List<GraphNode<T>>>()
    var currentNodes = mutableListOf<GraphNode<T>>()
    currentNodes.add(head)
    result.add(currentNodes)

    while (currentNodes.isNotEmpty()) {
        var nextNodes = mutableListOf<GraphNode<T>>()
        for (node in currentNodes) {
            node.left?.let { nextNodes.add(it) }
            node.right?.let { nextNodes.add(it) }
        }
        result.add(nextNodes)
        currentNodes = nextNodes
        nextNodes = mutableListOf()
    }
    return result
}

fun <T> getNodesListRecurs(nodes: MutableList<List<GraphNode<T>>>): MutableList<List<GraphNode<T>>> {
    val newNodes = mutableListOf<GraphNode<T>>()
    val lastNodes = nodes.last()

    for (node in lastNodes) {
        node.left?.let { newNodes.add(it) }
        node.right?.let { newNodes.add(it) }
    }

    return if (newNodes.isEmpty()) {
        nodes
    } else {
        nodes.add(newNodes)
        getNodesListRecurs(nodes)
    }
}