package crackin.Graphs

import GraphNode
import generateUniqueSortedArray
import showBinaryTree

//Validate 8ST: Implement a function to check ifa binary tree is a binary search tree.

fun main() {
    val array = generateUniqueSortedArray()
    val one = GraphNode(20)
    val two = GraphNode(10)
    val three = GraphNode(30)
    val four = GraphNode(25)
    one.right = three
    one.left = two
    two.right = four

    val tree = getMinSearchTree(array)!!
    showBinaryTree(tree)
    println(isSearchTree(one, null, null))
}

fun <T: Comparable<T>> isSearchTree(node: GraphNode<T>?, min: T?, max: T?): Boolean {
    if(node == null) return true

    if((min != null && node.value <= min) || (max != null && node.value > max)) {
        return false
    }

    val left = isSearchTree(node.left, min, node.value)
    if(!left) return false
    val right = isSearchTree(node.right, node.value, max)
    return right
}