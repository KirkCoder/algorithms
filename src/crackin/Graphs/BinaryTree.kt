package crackin.Graphs

import GraphNode
import generateArray
import getBinarySearchWikiTree
import showBinaryTree
import showList

fun main() {
    val array = generateArray()
    val listTree = BinaryTree<Int>()
    for (i in array) {
        listTree.insert(i)
    }
    showList(listTree.getOrderedList())

    val tree = getBinarySearchWikiTree()
//    tree.first[14]?.left = null
//    tree.first[14]?.right = tree.first[13]
    val bt = BinaryTree(tree.second)
    showList(bt.getOrderedList())
    bt.delete(10)
    bt.show()
    showList(bt.getOrderedList())
}

class BinaryTree<T : Comparable<T>>() {

    private var head: GraphNode<T>? = null

    constructor(head: GraphNode<T>) : this() {
        this.head = head
    }

    fun insert(value: T) {
        val tmpHead = head
        val node = GraphNode(value)
        if (tmpHead == null) {
            head = node
        } else {
            insert(tmpHead, node)
        }
    }

    fun getOrderedList(): List<T> {
        val order = mutableListOf<T>()
        head?.let { node ->
            getInOrder(node, order)
        }
        return order
    }

    fun delete(value: T) {
        var node = find(head, value)
        while (node != null) {
            delete(node)
            node = find(head, value)
        }
    }

    fun show() {
        head?.let { showBinaryTree(it) }
    }

    private fun delete(node: GraphNode<T>) {
        val parent = node.parent
        val right = node.right
        val left = right?.left
        if (right == null) {
            removeFromParent(parent, node, null)
            return
        }
        if (left == null) {
            removeFromParent(parent, node, right)
            right.left = node.left
            node.left?.parent = right.left
            invalidateHead(node, right)
            return
        }

        var successor = left
        var nextLeft = left.left

        while (nextLeft != null) {
            successor = nextLeft
            nextLeft = nextLeft.left
        }

        successor?.parent?.left = null
        if (successor?.right != null) {
            successor.parent?.left = successor.right
            successor.right?.parent = successor.parent
            successor.right = node.right
            successor.right?.parent = successor
        } else {
            successor?.left = node.left
            successor?.right = node.right
            successor?.left?.parent = successor
            successor?.right?.parent = successor
        }
        removeFromParent(parent, node, successor)
        invalidateHead(node, successor)
    }

    private fun invalidateHead(node: GraphNode<T>, successor: GraphNode<T>?) {
        if (node == head) {
            head = successor
            successor?.parent = null
        }
    }

    private fun removeFromParent(parent: GraphNode<T>?, current: GraphNode<T>, successor: GraphNode<T>?) {
        if (parent?.left == current) {
            parent.left = successor
        } else {
            parent?.right = successor
        }
    }

    private fun insert(current: GraphNode<T>, next: GraphNode<T>) {
        if (next.value >= current.value) {
            val right = current.right
            if (right == null) {
                current.right = next
            } else {
                insert(right, next)
            }
        } else {
            val left = current.left
            if (left == null) {
                current.left = next
            } else {
                insert(left, next)
            }
        }
    }

    private fun getInOrder(node: GraphNode<T>, order: MutableList<T>) {
        node.left?.let { getInOrder(it, order) }
        order.add(node.value)
        node.right?.let { getInOrder(it, order) }
    }

    private fun find(from: GraphNode<T>?, value: T): GraphNode<T>? {
        return when {
            from == null -> null
            from.value == value -> from
            value > from.value -> find(from.right, value)
            else -> find(from.left, value)
        }
    }
}