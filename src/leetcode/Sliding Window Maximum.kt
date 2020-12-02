package leetcode

import showIterable
import java.util.*


fun main() {
    val dec = ArrayDeque<Int>()
//    val arr = intArrayOf(1, 3, 1, 2, 0, 5)
    val arr = intArrayOf(1, 1, 4, 4, 4, 4)
    showIterable(maxSlidingWindow3(arr, 3).iterator())
}

fun maxSlidingWindow3(nums: IntArray, k: Int): IntArray {
    if (nums.size * k == 0) return IntArray(0)
    if (k == 1) return nums
    var j = 0
    val res = IntArray(nums.size - k + 1)
    val deque = LinkedList<Int>()

    var max = 0
    for (i in 0 until k) {
        clean(deque, nums, i, k)
        deque.add(nums[i])
        if (nums[i] > nums[max]) {
            max = i
        }
    }

    res[j++] = nums[max]

    for (i in k until nums.size) {
        clean(deque, nums, i, k)
        deque.add(nums[i])
        res[j++] = deque.first
    }
    return res
}

fun clean(deque: LinkedList<Int>, nums: IntArray, i: Int, k: Int) {
    if (deque.isNotEmpty() && i - k >= 0 && deque.first() == nums[i - k]) {
        deque.removeFirst()
    }

    while (deque.isNotEmpty() && nums[i] > deque.last) {
        deque.removeLast()
    }
}

fun maxSlidingWindow2(nums: IntArray, k: Int): IntArray {
    val res = mutableListOf<Int>()
    val queue = MaxQueue()
    for (i in nums.indices) {
        if (i + k > nums.size) {
            return res.toIntArray()
        } else {
            if (i > 0) {
                queue.peek()
                if (i + k - 1 < nums.size) {
                    queue.add(nums[i + k - 1])
                }
            } else {
                for (j in i until k) {
                    queue.add(nums[j])
                }
            }
            queue.max()?.let {
                res.add(it)
            }
        }
    }
    return res.toIntArray()
}


class MaxQueue {
    private val max = PriorityQueue<Node>() { f, s -> s.value.compareTo(f.value) }
    private val queue = LinkedList<Node>()

    fun max(): Int? {
        var m = max.peek()
        while (m.isDeleted) {
            max.poll()
            m = max.peek()
        }
        return m.value
    }

    fun add(element: Int) {
        val node = Node(element)
        max.add(node)
        queue.add(node)
    }

    fun peek(): Int? {
        val peek = queue.removeFirst()
        val m = max()
        if (peek.value == m) {
            max.poll()
        } else {
            peek.isDeleted = true
        }
        return peek.value
    }

    private class Node(
        val value: Int,
        var isDeleted: Boolean = false
    )
}


fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    val res = mutableListOf<Int>()
    for (i in nums.indices) {
        if (i + k > nums.size) {
            return res.toIntArray()
        } else {
            val max = getMax(i, k, nums)
            res.add(max)
        }
    }
    return res.toIntArray()
}

fun getMax(from: Int, k: Int, nums: IntArray): Int {
    var max = nums[from]
    for (i in from + 1 until from + k) {
        if (nums[i] > max) {
            max = nums[i]
        }
    }
    return max
}