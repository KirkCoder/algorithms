package crackin.Graphs

import GraphNode
import getDirectGraph
import showList
import java.util.*


//4.1 Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a route between two nodes.

fun main() {
    val graph = getDirectGraph()
    val from = graph[7]
    val to = graph[5]

    val route = findRoute(from, to, emptyList())
    showList(route.map { it.value })

    println(isRouteExist(from, to))
}

fun <T> isRouteExist(from: GraphNode<T>, to: GraphNode<T>): Boolean {
    if (from == to) {
        return true
    }
    val queue = LinkedList<GraphNode<T>>()
    val visited = LinkedList<GraphNode<T>>()
    visited.add(from)

    queue.addAll(from.adjacencs)

    while (queue.isNotEmpty()) {
        val node = queue.pop()
        if (!visited.contains(node)) {
            if (node == to) {
                return true
            }
            visited.add(node)
            queue.addAll(node.adjacencs)
        }
    }

    return false
}

fun <T> findRoute(from: GraphNode<T>, to: GraphNode<T>, visited: List<GraphNode<T>>): List<GraphNode<T>> {
    if (from == to) {
        return visited.copy().apply {
            add(to)
        }
    } else {
        if (from.adjacencs.isEmpty()) {
            return emptyList()
        } else {
            val list = visited.copy()
            list.add(from)
            for (descender in from.adjacencs) {
                if (!visited.contains(descender)) {
                    val nextRoute = findRoute(descender, to, list)
                    if (nextRoute.isNotEmpty()) {
                        return nextRoute
                    }
                }
            }
        }
    }
    return emptyList()
}

private fun <T> List<T>.copy(): MutableList<T> {
    val list = mutableListOf<T>()
    for (s in this) {
        list.add(s)
    }
    return list
}