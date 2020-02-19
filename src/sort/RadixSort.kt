package sort

import generateArray
import showArray
import java.util.*
import kotlin.NoSuchElementException

private const val DIVIDER_STEP = 10
val queues = Array<LinkedList<Int>>(10) { LinkedList() }

fun main() {
    val arr = generateArray(maxItemValue = 94445, length = 7685)
    showArray(arr)
    sortRadix(arr)
    showArray(arr)
}

fun sortRadix(arr: Array<Int>) {
    var isChange = true
    var divider = 1
    while (isChange) {
        isChange = false
        for (a in arr) {
            val tmp = a / divider
            queues[tmp % DIVIDER_STEP].add(a)
            if (tmp > 0) {
                isChange = true
            }
        }
        for (i in arr.indices) {
            arr[i] = getElementFromQueue()
        }
        divider *= DIVIDER_STEP
    }
}

fun getElementFromQueue(): Int {
    var position = 0
    while (position < 10) {
        try {
            return queues[position].pop()
        } catch (e: NoSuchElementException) {
            position++
        }
    }
    throw NoSuchElementException()
}