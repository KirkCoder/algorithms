package sort

import generateArray
import showArray


val arr = generateArray(76)

fun main() {
    showArray(arr)
    shellSort(arr, arr.size / 2)
    showArray(arr)
}

fun shellSort(arr: Array<Int>, step: Int) {
    for (i in 1 until arr.size step step) {
        val element = arr[i]
        for (j in i - step downTo 0 step step) {
            if (arr[j] >= element) {
                val tmp = arr[j + step]
                arr[j + step] = arr[j]
                arr[j] = tmp
            } else {
                break
            }
        }
    }
    if (step == 1) return
    shellSort(arr, step / 2)
}

fun sort(arr: Array<Int>) {
    for (i in 1 until arr.size) {
        val element = arr[i]
        for (j in i - 1 downTo 0) {
            if (arr[j] >= element) {
                val tmp = arr[j + 1]
                arr[j + 1] = arr[j]
                arr[j] = tmp
            } else {
                break
            }
        }
    }
}


