package crackin.SearchAndSort

import kotlin.experimental.and
import kotlin.experimental.or

//Missing Int: Given an input file with four billion non-negative integers, provide an algorithm to generate an integer that is not contained in the file. Assume you have 1 GB of memory available for this task.
//What if you have only 10MB of memory? Assume that all the values are distinct and we now have
//no more than one billion non-negative integers.

private const val MAX_VALUE = 3000

fun main() {
    val list = mutableListOf<Int>()
    for (i in 0..MAX_VALUE) {
        list.add(i)
    }
    list[233] = 0
    println(findExtraInteger(list))
}

fun findExtraInteger(list: List<Int>): Int {
    var i = 0L
    while (i <= MAX_VALUE.toLong()) {
        val to = if (i + 511 > MAX_VALUE.toLong()) MAX_VALUE else (i + 511).toInt()
        val res = findExtraInteger(list, i.toInt(), to)
        if (res != -1) return res
        i += 512L
    }
    return -1
}

fun findExtraInteger(list: List<Int>, from: Int, to: Int): Int {
    val bytes = ByteArray(64)
    for (i in list) {
        if (i in from..to) {
            val pos = (i - from) / 8
            val bytePos = (i - from) % 8
            val byte = bytes[pos]
            val mask = 1 shl (7 - bytePos)
            bytes[pos] = byte or mask.toByte()
        }
    }

    for (i in bytes.indices) {
        val byte = bytes[i]
        for (j in 0..7) {
            if ((i * 8) + (7 - j) > (to - from)) {
                return -1
            }
            if (byte and (1 shl j).toByte() == 0.toByte()) {
                return i * 8 + (7 - j) + from
            }
        }
    }

    return -1
}