package crackin.Graphs

import GraphNode
import generateArray
import showArray
import showBinaryTree

fun main() {
    // 75, 52, 38, 73, 55
    val arr = generateArray()
    showArray(arr)
    val tree = RedBlackTree<Int>()
    val l = listOf(82, 9, 26, 92, 8, 46, 0, 53, 78, 50, 84, 70)
    for (i in l) {
        tree.add(i)
    }
    tree.show()
}

class RedBlackTree<T : Comparable<T>> {

    var head: GraphNode<T>? = null

    fun add(value: T) {
        val node = GraphNode(value)
        val tmpHead = head
        if (tmpHead == null) {
            node.isBlack = true
            head = node
            return
        } else {
            findPlace(node, tmpHead)
        }

        if (node.parent?.isBlack == true) {
            return
        } else {
            invalidateTree(node)
        }
    }

    private fun invalidateTree(node: GraphNode<T>?) {
        if (node == null) return
        val parent = node.parent
        val pp = node.parent?.parent
        val isParentOnLeft = pp?.left == parent
        val isLeft = parent?.left == node

        when {
            isParentOnLeft && isLeft -> {
                pp?.let {
                    it.isBlack = !it.isBlack
                }
                parent?.let {
                    it.isBlack = !it.isBlack
                    rightRotation(it)
                }
            }
            !isParentOnLeft && !isLeft -> {
                pp?.let {
                    it.isBlack = !it.isBlack
                }
                parent?.let {
                    it.isBlack = !it.isBlack
                    leftRotation(it)
                }
            }
            isParentOnLeft && !isLeft -> {
                pp?.let {
                    it.isBlack = !it.isBlack
                }
                node.isBlack = !node.isBlack
                leftRotation(node)
                rightRotation(node)
            }
            else -> {
                pp?.let {
                    it.isBlack = !it.isBlack
                }
                node.isBlack = !node.isBlack
                rightRotation(node)
                leftRotation(node)
            }
        }
        invalidateHead()
    }

    private fun invalidateHead() {
        head?.isBlack = true
    }

    private fun findPlace(node: GraphNode<T>, current: GraphNode<T>) {
        if (current.left?.isBlack == false && current.right?.isBlack == false) {
            current.left?.isBlack = true
            current.right?.isBlack = true
            if (current !== head) {
                current.isBlack = false
            }
            if (!current.isBlack && current.parent?.isBlack == false) {
                invalidateTree(current)
            }
        }
        if (node.value >= current.value) {
            val right = current.right
            if (right == null) {
                node.parent = current
                current.right = node
            } else {
                findPlace(node, right)
            }
        } else {
            val left = current.left
            if (left == null) {
                node.parent = current
                current.left = node
            } else {
                findPlace(node, left)
            }
        }
    }

    private fun rightRotation(node: GraphNode<T>) {
        val parent = node.parent ?: return
        invalidateParent(node, parent)
        parent.left = node.right
        parent.left?.parent = parent
        parent.parent = node
        node.right = parent
    }

    private fun leftRotation(node: GraphNode<T>) {
        val parent = node.parent ?: return
        invalidateParent(node, parent)
        parent.right = node.left
        node.left?.parent = parent
        parent.parent = node
        node.left = parent
    }

    private fun invalidateParent(node: GraphNode<T>, parent: GraphNode<T>) {
        node.parent = parent.parent
        if (parent.parent?.left == parent) {
            parent.parent?.left = node
        } else {
            parent.parent?.right = node
        }
        parent.parent = node
        if (parent == head) {
            head = node
        }
    }

    fun show() {
        head?.let { showBinaryTree(it) }
        println()
    }
}