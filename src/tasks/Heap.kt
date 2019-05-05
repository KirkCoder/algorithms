package tasks

import sort.generateArray
import sort.showArray

fun main() {
    val arr = generateArray()
    showArray(arr)
    val heap = Heap<Int>()
    for (a in arr) {
        heap.insert(a)
    }
    heap.showList()
    var min = heap.extractMin()
    while (min != null) {
        println(min)
        heap.showList()
        min = heap.extractMin()
    }
    println("finished")
    heap.showList()
}

class Heap<T : Comparable<T>> {

    private val list = mutableListOf<T>()

    fun getMin(): T? {
        return if (list.isNotEmpty()) list[0]
        else null
    }

    fun insert(node: T) {
        list.add(node)
        moveUp(list.size - 1)
    }

    fun extractMin(): T? {
        return if (list.isNotEmpty()) {
            val element = list[0]
            if (list.size == 1){
                list.clear()
            } else {
                list[0] = list.removeAt(list.size - 1)
                moveDown()
            }
            element
        } else null
    }

    private fun moveDown() {
        var index = 0
        var nextIndex = 0
        var isMoved = true
        val element = list[0]

        do {
            val leftIndex = 2 * index + 1
            val rightIndex = 2 * index + 2
            when{
                leftIndex > list.size - 1 && rightIndex > list.size - 1 -> isMoved = false
                leftIndex > list.size - 1 || rightIndex > list.size - 1 -> {
                    if (leftIndex > list.size - 1) nextIndex = rightIndex
                    if (rightIndex > list.size - 1) nextIndex = leftIndex
                }
                else -> {
                    nextIndex = if (list[leftIndex] < list[rightIndex]) {
                        leftIndex
                    } else {
                        rightIndex
                    }
                }
            }

            if (element > list[nextIndex] && isMoved) {
                list[index] = list[nextIndex]
                index = nextIndex
                isMoved = true
            } else {
                isMoved = false
            }
        } while (isMoved)

        list[index] = element
    }

    private fun moveUp(index: Int) {
        var element = index
        val tmp = list[element]
        var parent = (element - 1) / 2
        while (element > 0 && list[parent] > tmp) {
            list[element] = list[parent]
            element = parent
            parent = (element - 1) / 2
        }
        list[element] = tmp
    }

    fun showList() {
        for (t in list) {
            print("$t, ")
        }
        println()
    }
}