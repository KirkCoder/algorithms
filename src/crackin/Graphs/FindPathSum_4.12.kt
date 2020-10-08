package crackin.Graphs

import GraphNode
import getBinarySearchWikiTree
import java.util.*

//Paths with Sum: You are given a binary tree in which each node contains an integer value (which might be positive or negative). Design an algorithm to count the number of paths that sum to a given value.The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

// custom task = find summ of path
fun main() {
    val tree = getBinarySearchWikiTree()

    val paths = getAllPaths(10, tree.second)

    println(findPathSumm(tree.first[10]!!, tree.first[13]!!))
    println(getPathSummWithParentLinks(tree.first[10]!!, tree.first[13]!!))

    println(findPathSumm(tree.first[8]!!, tree.first[7]!!))
    println(getPathSummWithParentLinks(tree.first[8]!!, tree.first[7]!!))
}


fun findPathCount(head: GraphNode<Int>, sum: Int): Int {
    return pathCount(head, 2, 0, mutableMapOf())
}

fun pathCount(head: GraphNode<Int>?, targetSum: Int, runningSum: Int, sums: MutableMap<Int, Int>): Int {
    if (head == null) return 0

    val newSum = runningSum + head.value

    val sum = newSum - targetSum

    var pathCount = sums[sum] ?: 0

    if (newSum == targetSum) pathCount++

    val s = sums[newSum] ?: 0 + 1
    sums[newSum] = s

    pathCount += pathCount(head.left, targetSum, newSum, sums)
    pathCount += pathCount(head.right, targetSum, newSum, sums)

    val ss = sums[newSum] ?: 0 - 1
    if (ss == 0) {
        sums.remove(newSum)
    } else {
        sums[newSum] = ss
    }

    return pathCount
}


fun getAllPaths(sum: Int, graph: GraphNode<Int>): MutableList<List<GraphNode<Int>>> {
    val nodes = LinkedList<GraphNode<Int>>()
    val newNodes = LinkedList<GraphNode<Int>>()
    newNodes.add(graph)

    while (newNodes.isNotEmpty()) {
        val next = newNodes.removeFirst()
        next.left?.let { newNodes.add(it) }
        next.right?.let { newNodes.add(it) }
        nodes.add(next)
    }

    val paths = mutableListOf<List<GraphNode<Int>>>()

    for (node in nodes) {
        if (node.value == 6) {
            println("odhjf")
        }
        getPath(sum, node, emptyList(), 0, paths)
    }

    return paths
}

fun getPath(
    sum: Int,
    head: GraphNode<Int>,
    path: List<GraphNode<Int>>,
    res: Int,
    paths: MutableList<List<GraphNode<Int>>>
) {

    val newRes = res + head.value
    val newPath = path.toMutableList()
    newPath.add(head)
    if (sum == newRes) {
        paths.add(newPath)
        return
    }

    head.left?.let { getPath(sum, it, newPath, newRes, paths) }
    head.right?.let { getPath(sum, it, newPath, newRes, paths) }
}


// custom

fun findPathSumm(start: GraphNode<Int>, end: GraphNode<Int>): Int {
    return findSum(findPath(start, end))
}


private fun findPath(next: GraphNode<Int>?, target: GraphNode<Int>): MutableList<GraphNode<Int>> {

    if (next == null) return mutableListOf()

    if (next.value == target.value) {
        return mutableListOf(next)
    }

    val leftPath = findPath(next.left, target)
    val rightPath = findPath(next.right, target)

    if (leftPath.isNotEmpty()) {
        leftPath.add(next)
        return leftPath
    }

    if (rightPath.isNotEmpty()) {
        rightPath.add(next)
        return rightPath
    }

    return mutableListOf()
}


fun getPathSummWithParentLinks(start: GraphNode<Int>, end: GraphNode<Int>): Int {
    val path = findPathWithParentLinks(start, end)
    return findSum(path)
}

private fun <T> findPathWithParentLinks(start: GraphNode<T>, end: GraphNode<T>): List<GraphNode<T>> {

    val nexts = mutableListOf<GraphNode<T>>()

    var next: GraphNode<T>? = end

    while (next != null && next.value != start.value) {
        nexts.add(next)
        next = next.parent
    }

    return if (next == null) {
        emptyList()
    } else {
        nexts.add(start)
        nexts.reverse()
        nexts
    }

}

private fun findSum(path: List<GraphNode<Int>>): Int {
    var sum = 0
    for (node in path) {
        sum += node.value
    }
    return sum
}