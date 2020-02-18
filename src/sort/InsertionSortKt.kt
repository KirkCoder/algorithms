package sort

import generateArray
import showArray

fun main() {
    val arr = generateArray()
    showArray(arr)
    sort(arr)
    showArray(arr)
}

fun <T : Comparable<T>> sort(current: Array<T>) {
    for (i in 1 until current.size) {
        val element = current[i]
        for (j in i - 1 downTo 0) {
            if (current[j] > element) {
                current[j + 1] = current[j]
                current[j] = element
            } else {
                break
            }
        }
    }
}