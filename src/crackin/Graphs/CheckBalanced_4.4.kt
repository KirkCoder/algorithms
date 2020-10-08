package crackin.Graphs

import GraphNode
import generateUniqueSortedArray
import getNotBalancedBinaryTree
import kotlin.math.abs
import kotlin.math.max

//Implement a function to check if a binary tree is balanced. For the purposes of this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any node never differ by more than one.

fun main() {
    val array = generateUniqueSortedArray()
//    println(isTreeBalanced(tree!!))
    println(isTreeBalanced(getNotBalancedBinaryTree()))
}

fun <T> isTreeBalanced(head: GraphNode<T>): Boolean {
    return getTreeLength(head) != -1
}

private fun <T> getTreeLength(node: GraphNode<T>): Int {
    val left = node.left
    val right = node.right

    if (left == null && right == null) {
        return 1
    }

    if (left == null) {
        return getTreeLength(right!!) + 1
    }

    if (right == null) {
        return getTreeLength(left) + 1
    }

    val leftSize = getTreeLength(left)
    val rightSize = getTreeLength(right)

    return if (abs(leftSize - rightSize) > 1) {
        -1
    } else {
        max(leftSize, rightSize) + 1
    }
}