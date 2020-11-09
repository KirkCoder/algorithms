package crackin.SearchAndSort

import generateArray
import showArray

//Sorted Merge: You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B. Write a method to merge Binto A in sorted order.

fun main() {
    val tmp = generateArray(length = 20)
    tmp.sort()
    val first = Array<Int>(30){ -1 }
    for (i in tmp.indices) {
        first[i] = tmp[i]
    }
    val second = generateArray(length = 10)
    second.sort()
    val res = mergeSmart(first, second)
    showArray(res)
}

fun mergeSmart(first: Array<Int>, second: Array<Int>): Array<Int> {
    var i = 1
    var tmp = first[0]
    while(tmp != -1 && i < first.size) {
        tmp = first[i]
        i++
    }
    i--
    i--

    var f = first.size - 1
    var s = second.size - 1

    while (i > -1 && s > -1) {
        if (first[i] > second[s]) {
            first[f--] = first[i--]
        } else {
            first[f--] = second[s--]
        }
    }

    while (i > -1) {
        first[f--] = first[i--]
    }

    while (s > -1) {
        first[f--] = second[s--]
    }

    return first
}

fun mergeArrays(first: Array<Int>, second: Array<Int>): Array<Int> {
    var i = 1
    var tmp = first[0]
    while(tmp != -1 && i < first.size) {
        tmp = first[i]
        i++
    }
    i--
    i--
    var j = first.size - 1
    while(i > -1) {
        first[j] = first[i]
        i--
        j--
    }
    j++
    var f = 0
    var s = 0
    while(j < first.size && s < second.size) {
        if(first[j] > second[s]) {
            first[f++] = second[s++]
        } else {
            first[f++] = first[j++]
        }
    }

    while(j < first.size) {
        first[f++] = first[j++]
    }

    while(s < second.size) {
        first[f++] = second[s++]
    }

    return first
}