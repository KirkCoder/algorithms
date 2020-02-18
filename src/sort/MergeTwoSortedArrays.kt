package sort

import generateArray
import showArray

fun main() {
    val first = generateArray()
    first.sort()
    val second = generateArray()
    second.sort()
    val tmpArr = Array<Int>(first.size * 2) { -1 }
    for ((i, v) in first.withIndex()) {
        tmpArr[i] = v
    }
    merge(first, tmpArr)
    showArray(tmpArr)
}

fun merge(left: Array<Int>, right: Array<Int>) {
    var iLeft = left.size - 1
    var iRight = left.size - 1
    var iLong = right.size - 1
    while (iLong > -1) {
        when {
            iLeft > -1 && iRight > -1 -> {
                if (left[iLeft] > right[iRight]) {
                    right[iLong--] = left[iLeft--]
                } else {
                    right[iLong--] = right[iRight--]
                }
            }
            iLeft > -1 -> right[iLong--] = left[iLeft--]
            iRight > -1 -> right[iLong--] = right[iRight--]
        }
    }
}
