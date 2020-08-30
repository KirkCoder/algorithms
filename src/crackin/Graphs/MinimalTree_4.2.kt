package crackin.Graphs

import GraphNode
import generateArray
import showBinaryTree

//Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary search tree with minimal height.

fun main() {
    val array = generateArray()
//    val array = arrayOf(1)
    array.sort()
    val tree = getMinSearchTree(array)
    showBinaryTree(tree!!)
}

fun <T : Comparable<T>> getMinSearchTree(array: Array<T>): GraphNode<T>? {
    return getTreeNode(0, array.size - 1, array)
}

private fun <T : Comparable<T>> getTreeNode(from: Int, to: Int, array: Array<T>): GraphNode<T>? {

    if (to < from) {
        return null
    }

    val middle = (from + to) / 2
    val node = GraphNode(array[middle])

    node.left = getTreeNode(from, middle - 1, array)
    node.right = getTreeNode(middle + 1, to, array)
    return node
}

