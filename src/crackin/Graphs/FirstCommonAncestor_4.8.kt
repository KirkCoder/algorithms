package crackin.Graphs

import GraphNode
import getBinarySearchWikiTree

//First Common Ancestor: Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE:This is not necessarily a binary search tree.

fun main() {
    val pair = getBinarySearchWikiTree()
    println(findFirstCommonAncestorBad(pair.first[1]!!, pair.first[13]!!)?.value)
    println(getFirstCommonAncestor(pair.first[1]!!, pair.first[13]!!)?.value)
    println(getFirstCommonAncestor(pair.second, pair.first[1]!!, pair.first[13]!!)?.value)
    println(
        getFirstCommonAncestorMemo(
            pair.second,
            pair.first[1]!!,
            pair.first[13]!!,
            mutableMapOf(),
            mutableMapOf()
        )?.value
    )
}

fun <T> getFirstCommonAncestorMemo(
    root: GraphNode<T>?,
    first: GraphNode<T>,
    second: GraphNode<T>,
    memoFirst: MutableMap<GraphNode<T>, Direction>,
    memoSecond: MutableMap<GraphNode<T>, Direction>
): GraphNode<T>? {

    if (root == null) return null
    if (root == first) return first
    if (root == second) return second

    val leftFirst = isNodeExistMemo(root.left, first, memoFirst)
    val rightFirst = if (!leftFirst) isNodeExistMemo(root.right, first, memoSecond) else false

    val rightSecond = isNodeExistMemo(root.right, second, memoFirst)
    val leftSecond = if (!rightSecond) isNodeExistMemo(root.left, second, memoSecond) else false

    if ((!leftFirst && !rightFirst) || (!leftSecond && !rightSecond)) {
        return null
    }

    if ((leftFirst && rightSecond) || (rightFirst && leftSecond)) {
        return root
    }

    if (leftFirst && leftSecond) {
        return getFirstCommonAncestorMemo(root.left, first, second, memoFirst, memoSecond)
    }
    if (rightFirst && rightSecond) {
        return getFirstCommonAncestorMemo(root.right, first, second, memoFirst, memoSecond)
    }

    return null
}

private fun <T> isNodeExistMemo(
    root: GraphNode<T>?,
    node: GraphNode<T>?,
    memo: MutableMap<GraphNode<T>, Direction>
): Boolean {
    if (root == null) return false
    if (node == null) return false
    if (memo[root] != null) return true
    return when {
        root == node -> true
        isNodeExist(root.left, node) -> {
            memo[root] = Direction.Left
            true
        }
        isNodeExist(root.right, node) -> {
            memo[root] = Direction.Right
            true
        }
        else -> false
    }
}

sealed class Direction {
    object Left : Direction()
    object Right : Direction()
}

fun <T> getFirstCommonAncestor(root: GraphNode<T>?, first: GraphNode<T>, second: GraphNode<T>): GraphNode<T>? {

    if (root == null) return null
    if (root == first) return first
    if (root == second) return second

    val leftFirst = isNodeExist(root.left, first)
    val rightFirst = if (!leftFirst) isNodeExist(root.right, first) else false

    val rightSecond = isNodeExist(root.right, second)
    val leftSecond = if (!rightSecond) isNodeExist(root.left, second) else false

    if ((!leftFirst && !rightFirst) || (!leftSecond && !rightSecond)) {
        return null
    }

    if (leftFirst && leftSecond) {
        return getFirstCommonAncestor(root.left, first, second)
    }
    if (rightFirst && rightSecond) {
        return getFirstCommonAncestor(root.right, first, second)
    }

    if ((leftFirst && rightSecond) || (rightFirst && leftSecond)) {
        return root
    }

    return null
}

private fun <T> isNodeExist(root: GraphNode<T>?, node: GraphNode<T>?): Boolean {
    if (root == null) return false
    if (node == null) return false
    return if (root == node) {
        true
    } else {
        isNodeExist(root.left, node) || isNodeExist(root.right, node)
    }
}

fun <T> getFirstCommonAncestor(first: GraphNode<T>, second: GraphNode<T>): GraphNode<T>? {
    val firstDepth = getDepth(first)
    val secondDepth = getDepth(second)

    var small: GraphNode<T>? = if (firstDepth <= secondDepth) first else second
    var big: GraphNode<T>? = if (firstDepth > secondDepth) first else second

    big = adjustDepth(big, Math.abs(firstDepth - secondDepth))

    while (small != null && big != null && small != big) {
        small = small.parent
        big = big.parent
    }

    return if (small != null && big != null) {
        small
    } else {
        null
    }
}

private fun <T> adjustDepth(node: GraphNode<T>?, diff: Int): GraphNode<T>? {
    var tmpDiff = diff
    var tmpNode: GraphNode<T>? = node
    while (tmpDiff != 0 && tmpNode != null) {
        tmpNode = tmpNode.parent
        tmpDiff--
    }
    return tmpNode
}

private fun <T> getDepth(node: GraphNode<T>?): Int {
    var depth = 0
    var next = node?.parent
    while (next != null) {
        next = next.parent
        depth++
    }
    return depth
}

fun <T> findFirstCommonAncestorBad(first: GraphNode<T>, second: GraphNode<T>): GraphNode<T>? {
    if (first == second) return first
    var firstParent = first.parent
    var secondParent = second.parent
    val set = mutableSetOf<GraphNode<T>>()
    set.add(first)
    set.add(second)
    while (firstParent != null && secondParent != null) {
        if (firstParent == secondParent) {
            return firstParent
        }

        if (set.contains(firstParent)) {
            return firstParent
        } else {
            set.add(firstParent)
        }


        if (set.contains(secondParent)) {
            return secondParent
        } else {
            set.add(secondParent)
        }

        firstParent = firstParent.parent
        secondParent = secondParent.parent
    }

    while (firstParent != null) {
        if (set.contains(firstParent)) {
            return firstParent
        } else {
            set.add(firstParent)
        }
        firstParent = firstParent.parent
    }

    while (secondParent != null) {
        if (set.contains(secondParent)) {
            return secondParent
        } else {
            set.add(secondParent)
        }
        secondParent = secondParent.parent
    }

    return null
}