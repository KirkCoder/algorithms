package crackin.Graphs

import GraphNode
import getBinarySearchWikiTree
import getDirectGraph
import getNotOrientedGraph
import showIterable
import java.util.*

fun main() {
    val graph = getBinarySearchWikiTree()
    val res = findPathLength(graph.first[3]!!, graph.first[10]!!)
    println(res)

    val notOrientedGraph = getNotOrientedGraph()
    val path = getPathInNotOrientedGraph(notOrientedGraph[0], notOrientedGraph[4])
    showIterable(path.map { it.value }.iterator())
}

fun getPathInNotOrientedGraph(start: GraphNode<Int>, end: GraphNode<Int>): List<GraphNode<Int>> {
    val path = mutableListOf<GraphNode<Int>>()
    start.visited = true
    path.add(start)
    return getPathInNotOrientedGraph(start, end, path)
}

private fun getPathInNotOrientedGraph(
    start: GraphNode<Int>,
    end: GraphNode<Int>,
    path: List<GraphNode<Int>>
): List<GraphNode<Int>> {
    if (start == end) {
        return path
    }

    if (start.adjacencs.contains(end)) {
        val newPath = mutableListOf<GraphNode<Int>>()
        newPath.addAll(path)
        newPath.add(end)
        return newPath
    }

    for (next in start.adjacencs) {
        if (!next.visited) {
            next.visited = true
            val newPath = mutableListOf<GraphNode<Int>>()
            newPath.addAll(path)
            newPath.add(next)
            val nextPath = getPathInNotOrientedGraph(next, end, newPath)
            if (nextPath.isNotEmpty()) return nextPath
        }
    }

    return emptyList()
}

fun findPathLength(start: GraphNode<Int>, end: GraphNode<Int>): Int {
    val nodes = mutableMapOf<GraphNode<Int>, Int>()
    val tmpNodes = LinkedList<GraphNode<Int>>()

    tmpNodes.add(start)

    nodes[start] = 0

    while (tmpNodes.isNotEmpty()) {
        val node = tmpNodes.removeFirst()
        val parentDistance = nodes[node]
        val left = node.left
        val right = node.right
        appendDistances(parentDistance, left, nodes, tmpNodes)
        appendDistances(parentDistance, right, nodes, tmpNodes)
    }

    return nodes[end] ?: -1
}

private fun appendDistances(
    parentDistance: Int?,
    node: GraphNode<Int>?,
    nodes: MutableMap<GraphNode<Int>, Int>,
    tmpNodes: LinkedList<GraphNode<Int>>
) {
    if (parentDistance != null && node != null && nodes[node] == null) {
        nodes[node] = parentDistance + 1
        tmpNodes.add(node)
    }
}