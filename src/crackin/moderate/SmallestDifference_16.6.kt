package crackin.moderate

import generateArray
import showArray
import java.lang.Math.abs

//Smallest Difference: Given two arrays of integers, compute the pair of values (one value in each array) with the smallest (non-negative) difference. Return the difference.
//EXAMPLE
//Input{1,3,15,11,2h{2~12~235,1~8}
//    Output 3.That is, the pair (11, 8)

fun main() {
    val left = generateArray(length = 10)
    left[left.size - 1] = 122
    val right = generateArray(length = 10)
    right[left.size - 1] = 122

    getSmallestDiff(left, right)?.let {
        println("${it.first}, ${it.second}")
    }
}

fun getSmallestDiff(first: Array<Int>, second: Array<Int>): Pair<Int, Int>? {

    if (first.isEmpty() || second.isEmpty()) {
        return null
    }

    first.sort()
    second.sort()

    showArray(first)
    showArray(second)

    var f = 0
    var s = 0
    var res = Pair(first[f], second[s])
    while (f < first.size && s < second.size) {
        val fv = first[f]
        val sv = second[s]
        if (abs(res.first - res.second) > abs(fv - sv)) {
            res = Pair(fv, sv)
        }
        when {
            fv == sv -> {
                return Pair(first[f], second[s])
            }
            fv > sv && s < second.size -> {
                s++
            }
            f < first.size -> {
                f++
            }
            else -> {
                return res
            }
        }
    }

    return res
}