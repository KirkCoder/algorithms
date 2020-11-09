package crackin.SearchAndSort

import generateArray
import showList

//Sorted Search, No Size: You are given an array-like data structure Listy which lacks a size method. It does, however, have an elementAt (i) method that returns the element at index i in 0(1) time. If i is beyond the bounds of the data structure, it returns - 1. (For this reason, the data structure only supports positive integers.) Given a Listy which contains sorted, positive integers, find the index at which an element x occurs. If x occurs multiple times, you may return any index

fun main() {
    val list = generateArray().toMutableList()
    list.add(78)
    list.add(1)
    list.add(1)
    list.sort()
    val listy = Listy(list)
    println(list.indexOf(78))
    showList(list)
    println(findInListy(78, listy))
}

fun findInListy(value: Int, listy: Listy): Int {
    var i = 1
    while (listy.get(i) != -1 && listy.get(i) <= value) {
        i *= 2
    }
    return binarySearch(listy, value, i / 2, i)
}

fun binarySearch(listy: Listy, value: Int, from: Int, to: Int): Int {
    var middle: Int
    var low = from
    var high = to
    while (low <= high) {
        middle = (low + high) / 2
        val element = listy.get(middle)
        if (element > value || element == -1) {
            high = middle - 1
        } else if (element < value) {
            low = middle + 1
        } else {
            return middle
        }
    }
    return -1
}

class Listy(private val list: List<Int>) {
    fun get(i: Int): Int {
        return try {
            list[i]
        } catch (e: IndexOutOfBoundsException) {
            -1
        }
    }
}
