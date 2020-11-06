package test

import generateArray
import showArray
import java.util.*
import kotlin.NoSuchElementException


fun main() {
    val array = generateArray()
    radixSort(array)
    showArray(array)
}

fun radixSort(array: Array<Int>) {
    val queues = Array<LinkedList<Int>>(10) { LinkedList() }
    var isChange = true
    var divider = 1
    while (isChange) {
        isChange = false
        for (i in array.indices) {
            val tmp = array[i] / divider
            queues[tmp % 10].add(array[i])
            if (tmp > 0) {
                isChange = true
            }
        }
        for (i in array.indices) {
            array[i] = queues.pop()
        }
        divider *= 10
    }
}

fun Array<LinkedList<Int>>.pop(): Int {
    var i = 0
    while (i < 10) {
        try {
            return this[i].pop()
        } catch (e: Throwable) {
            i++
        }
    }
    throw NoSuchElementException()
}
