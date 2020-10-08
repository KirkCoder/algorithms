package crackin.Graphs

import java.util.*

//Random Node: You are implementing a binary search tree class from scratch, which, in addition to insert, find, and delete, has a method getRandomNode() which returns a random node from the tree. All nodes should be equally likely to be chosen. Design and implement an algorithm f o r g e t Ra n d o m N o d e , a n d e x p l a i n h o w y o u w o u l d i m p l e m e n t t h e r e s t o f t h e m e t h o d s .


class Tree<T : Comparable<T>> {
    private var head: Node<T>? = null

    fun add(value: T) {
        if (head == null) {
            head = Node(value)
        } else {
            head!!.add(value)
        }
    }

    fun getRandomNode(): Node<T>? {
        if (head == null) return null
        val random = Random().nextInt(head!!.size)
        return head!!.getRandomNode(random)
    }
}

class Node<T : Comparable<T>>(
    val value: T
) {

    var size = 1
        private set

    private var left: Node<T>? = null
    private var right: Node<T>? = null

    fun add(next: T) {
        size++
        if (next <= value) {
            if (left == null) {
                left = Node(next)
            } else {
                left!!.add(next)
            }
        } else {
            if (right == null) {
                right = Node(next)
            } else {
                right!!.add(next)
            }
        }
    }

    fun getRandomNode(random: Int): Node<T> {
        val leftSize = left?.size ?: 0
        return when {
            random < leftSize -> left!!.getRandomNode(random)
            random == leftSize -> this
            else -> right!!.getRandomNode(random - (leftSize + 1))
        }
    }

}













