package crackin.Graphs

import GraphNode
import getBinarySearchWikiTree
import java.util.*

fun main() {
    val tree = getBinarySearchWikiTree()
    val first = tree.second
    tree.first[6]?.right = null
    val second = getBinarySearchWikiTree().first[6]!!
    println(isSubTree(first, second))
    println(isSubTree2(first, second))
    println(isSubTree3(first, second))
}

fun <T> isSubTree3(first: GraphNode<T>, second: GraphNode<T>): Boolean {
    val nodes = LinkedList<GraphNode<T>>()
    nodes.add(first)
    while(nodes.isNotEmpty()) {
        val next = nodes.removeFirst()
        next.left?.let { nodes.add(it) }
        next.right?.let { nodes.add(it) }

        if(next.value == second.value && checkSubTree(next, second)) {
            return true
        }
    }
    return false
}

private fun <T> checkSubTree(first: GraphNode<T>?, second: GraphNode<T>?): Boolean {
    if(first == null && second == null) return true
    if(first != null && second == null) return true
    if(first == null && second != null) return false
    return if(first?.value == second?.value) {
        checkSubTree(first?.left, second?.left) && checkSubTree(first?.right, second?.right)
    } else {
        false
    }
}

fun <T> isSubTree2(first: GraphNode<T>, second: GraphNode<T>): Int {
    val firstTree = getTreeInList(first, true).joinToString(transform = ::mapToString, separator = "")
    val secondTree = getTreeInList(second, true).joinToString(transform = ::mapToString, separator = "")
    println(firstTree)
    println(secondTree)
    return firstTree.indexOf(secondTree)
}

private fun <T> mapToString(optional: Optional<T>): String {
    return if (optional is Optional.Exist) {
        optional.value.toString()
    } else {
        "X"
    }
}

fun <T> isSubTree(first: GraphNode<T>, second: GraphNode<T>): Boolean {
    val firstTreeList = getTreeInList(first, true)
    val secondTreeList = getTreeInList(second, false)
    for (i in firstTreeList.indices) {
        val value = firstTreeList[i]
        if (value is Optional.Exist && value.value == second.value && checkIsSubTree(
                i,
                firstTreeList,
                secondTreeList
            )
        ) {
            return true
        }
    }
    return false
}

private fun <T> checkIsSubTree(i: Int, first: List<Optional<T>>, second: List<Optional<T>>): Boolean {
    var j = i
    var k = 0
    while (k < second.size) {
        if (j >= first.size) return false
        val f = first[j]
        val l = second[k]
        when {
            f is Optional.Empty -> {
                j++
            }
            f is Optional.Exist && l is Optional.Exist && f.value != l.value -> {
                return false
            }
            else -> {
                j++
                k++
            }
        }
    }
    return true
}

private fun <T> getTreeInList(node: GraphNode<T>, withNulls: Boolean): List<Optional<T>> {
    val result = mutableListOf<Optional<T>>()
    appendNode(node, result, withNulls)
    return result
}

private fun <T> appendNode(node: GraphNode<T>?, nodes: MutableList<Optional<T>>, withNulls: Boolean) {
    when {
        node != null -> {
            nodes.add(Optional.Exist(node.value))
            appendNode(node.left, nodes, withNulls)
            appendNode(node.right, nodes, withNulls)
        }
        withNulls -> {
            nodes.add(Optional.Empty())
        }
    }
}

private sealed class Optional<T> {
    data class Exist<T>(val value: T) : Optional<T>()
    class Empty<T> : Optional<T>()
}














