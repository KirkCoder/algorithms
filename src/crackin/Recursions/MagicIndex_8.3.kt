package crackin.Recursions

import generateArray
import kotlin.math.max
import kotlin.math.min

//Magic Index: A magic index in an array A[1. .. n-1] is defined to be an index such that A[ i]
//i. Given a sorted array of distinct integers, write a method to find a magic index, if one exists, in array A.
//FOLLOW UP
//What if the values are not distinct?

fun main() {
//    val array = generateArray()
//    array.sort()
    val array = arrayOf(-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13)
//    println(findMagicIndex(array))
    println(findMagicIndexInNotDistinct(array))
}

fun findMagicIndexInNotDistinct(array: Array<Int>): Int {
    return findMagicIndexInNotDistinct(0, array.size - 1, array)
}


fun findMagicIndexInNotDistinct(from: Int, to: Int, array: Array<Int>): Int {
    if (to < from) return -1
    val middle = (to + from) / 2
    val value = array[middle]
    if (value == middle) {
        return value
    }
    val right = findMagicIndexInNotDistinct(max(value, middle + 1), to, array)
    if (right != -1) return right
    return findMagicIndexInNotDistinct(from, min(value, middle - 1), array)
}

fun findMagicIndex(array: Array<Int>): Int {
    return findMagicIndex(0, array.size - 1, array)
}

private fun findMagicIndex(from: Int, to: Int, array: Array<Int>): Int {
    if (to < from) return -1
    val middle = (from + to) / 2
    val value = array[middle]
    if (value == middle) {
        return value
    }
    return if (value > middle) {
        findMagicIndex(from, middle - 1, array)
    } else {
        findMagicIndex(middle + 1, to, array)
    }
}