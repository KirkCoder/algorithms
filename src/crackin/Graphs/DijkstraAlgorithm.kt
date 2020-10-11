package crackin.Graphs

import showList
import java.util.*

fun main() {
    val graph = arrayOf(
        //      A, B, C, D, E, F, G, H, I
        arrayOf(0, 5, 3, 0, 2, 0, 0, 0, 0), //A
        arrayOf(0, 0, 0, 2, 0, 0, 0, 0, 0), //B
        arrayOf(0, 1, 0, 1, 0, 0, 0, 0, 0), //C
        arrayOf(1, 0, 0, 0, 0, 0, 2, 1, 0), //D
        arrayOf(1, 0, 0, 0, 0, 0, 0, 4, 7), //E
        arrayOf(0, 3, 0, 0, 0, 0, 1, 0, 0), //F
        arrayOf(0, 0, 3, 0, 0, 0, 0, 0, 2), //G
        arrayOf(0, 0, 2, 0, 0, 2, 2, 0, 0), //H
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)  //I
    )

    showList(findPathByDijkstra(graph, 0, 8))
}

private fun findPathByDijkstra(graph: Array<Array<Int>>, from: Int, to: Int): MutableList<Int> {
    val queue = getPriorityQueue()
    val nodes = mutableListOf<DijNode>()
    for (i in graph.indices) {
        val node = DijNode(i)
        if (i == from) {
            node.weight = 0
        }
        queue.add(node)
        nodes.add(node)
    }

    findNextEdge(graph, nodes, queue)
    val path = mutableListOf<Int>()
    var current = nodes[to]
    while (current.value != from) {
        path.add(current.value)
        current = nodes[current.prev!!]
    }
    path.add(from)
    path.reverse()
    return path
}

private fun findNextEdge(
    graph: Array<Array<Int>>,
    nodes: MutableList<DijNode>,
    queue: PriorityQueue<DijNode>
): PriorityQueue<DijNode> {
    val next = queue.poll()
    next.removed = true
    val adjacencs = graph.getAdjacencs(next.value)
    for (adjacenc in adjacencs) {
        if (!nodes[adjacenc.value].removed) {
            val newWeight = next.weight + graph[next.value][adjacenc.value]
            if (newWeight < nodes[adjacenc.value].weight) {
                nodes[adjacenc.value].weight = newWeight
                nodes[adjacenc.value].prev = adjacenc.prev
            }
        }
    }
    nodes.show()
    val newQueue = getPriorityQueue()
    newQueue.addAll(queue)
    return if (newQueue.isNotEmpty()) {
        findNextEdge(graph, nodes, newQueue)
    } else {
        newQueue
    }
}

private fun getPriorityQueue(): PriorityQueue<DijNode> {
    return PriorityQueue<DijNode>(kotlin.Comparator { first, second ->
        first.weight.compareTo(second.weight)
    })
}

private fun List<DijNode>.show() {
    forEach {
        if (it.removed) {
            println("value: ${it.value}, removed: ${it.removed}")
        } else {
            println("value: ${it.value}, weight: ${it.weight}, prev: ${it.prev}, removed: ${it.removed}")
        }
    }
    println()
}

private fun Array<Array<Int>>.getAdjacencs(index: Int): List<DijNode> {
    val row = this[index]
    val adjacenncs = mutableListOf<DijNode>()
    for (i in row.indices) {
        if (row[i] != 0) {
            adjacenncs.add(
                DijNode(
                    value = i,
                    weight = row[i],
                    prev = index
                )
            )
        }
    }
    return adjacenncs
}

private class DijNode(
    val value: Int,
    var weight: Int = Int.MAX_VALUE,
    var prev: Int? = null,
    var removed: Boolean = false
)