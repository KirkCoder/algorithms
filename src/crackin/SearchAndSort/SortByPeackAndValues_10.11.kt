package crackin.SearchAndSort

import generateArray
import showArray

//Peaks and Valleys: In an array of integers, a "peak" is an element which is greater than or equal to the adjacent integers and a "valley" is an element which is less than or equal to the adjacent integers. For example, in the array {S, 8, 6, 2, 3, 4, 6}, {8, 6} are peaks and {S, 2} are valleys. Given an array of integers, sort the array into an alternating sequence of peaks and valleys.
//SOLUTION
//EXAMPLE
//Input: {5, 3, 1,2, 3} Output: {5, 1,3,2, 3}

fun main() {
    val array = arrayOf(0, 1, 4, 7, 8, 9)
//    val array = generateArray()
//    sortWithPicks(array)
    swapWithPicks(array)
    showArray(array)
}

fun swapWithPicks(array: Array<Int>) {
    for (i in 1 until array.size step 2) {
        val left = array[i - 1]
        val middle = array[i]
        val right = if (i + 1 < array.size) array[i + 1] else Int.MIN_VALUE
        val maxIndex = when (maxOf(left, middle, right)) {
            left -> i - 1
            middle -> i
            else -> i + 1
        }
        if (i != maxIndex) {
            val tmp = array[i]
            array[i] = array[maxIndex]
            array[maxIndex] = tmp
        }
    }
}

fun sortWithPicks(array: Array<Int>) {
    array.sort()

    for (i in 1 until array.size step 2) {
        val tmp = array[i - 1]
        array[i - 1] = array[i]
        array[i] = tmp
    }
}