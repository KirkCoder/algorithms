package crackin.SearchAndSort

import generateArray
import showArray

//Sort Big File: Imagine you have a 20 GB file with one string per line. Explain how you would sort the file.

fun main() {
    val array = generateArray()
    quickSort(array)
    showArray(array)
}

fun quickSort(array: Array<Int>) {
    sort(array, 0, array.size - 1)
}

fun sort(array: Array<Int>, from: Int, to: Int) {
    if (from > to) return

    val middle = array[(from + to) / 2]
    array[(from + to) / 2] = array[to]

    var less = from
    for (i in from until to) {
        if (array[i] < middle) {
            val tmp = array[less]
            array[less] = array[i]
            array[i] = tmp
            less++
        }
    }
    array[to] = array[less]
    array[less] = middle

    sort(array, from, less - 1)
    sort(array, less + 1, to)
}