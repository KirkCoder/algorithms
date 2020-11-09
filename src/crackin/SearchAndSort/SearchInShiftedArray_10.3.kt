package crackin.SearchAndSort

//Search in Rotated Array: Given a sorted array of n integers that has been rotated an unknown number of times, write code to find an element in the array. You may assume that the array was originally sorted in increasing order.
//EXAMPLE
//InputfindSin {15, 16, 19, 20, 25, 1, 3 ,4 ,5 ,7 ,1 0 , 14} Output 8 (the index of 5 in the array)

fun main() {
//    val array = arrayOf(15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14)
//    val array = arrayOf(50, 5, 20, 30, 40)
    val array = arrayOf(4, 2, 2, 2, 2, 3)
    println(findInShiftedArray(array, 3).toString())
}

fun findInShiftedArray(array: Array<Int>, value: Int): Int {
    return findInShiftedArray(array, value, 0, array.size - 1)
}

fun findInShiftedArray(array: Array<Int>, value: Int, from: Int, to: Int): Int {
    if (from > to) throw NoSuchElementException()
    val middle = (from + to) / 2
    val middleValue = array[middle]
    if (middleValue == value) return middle
    return if (value > middleValue) {
        if (array[to] > middleValue) {
            if (value <= array[to]) {
                findInShiftedArray(array, value, middle + 1, to)
            } else {
                findInShiftedArray(array, value, from, middle - 1)
            }
        } else {
            try {
                findInShiftedArray(array, value, middle + 1, to)
            } catch (e: NoSuchElementException) {
                findInShiftedArray(array, value, from, middle - 1)
            }
        }
    } else {
        if (array[from] < middleValue) {
            if (value >= array[from]) {
                findInShiftedArray(array, value, middle + 1, to)
            } else {
                findInShiftedArray(array, value, from, middle - 1)
            }
        } else {
            try {
                findInShiftedArray(array, value, middle + 1, to)
            } catch (e: NoSuchElementException) {
                findInShiftedArray(array, value, from, middle - 1)
            }
        }
    }
}