package crackin.SearchAndSort

import showIterable
import showList
import java.util.*
import kotlin.experimental.and
import kotlin.experimental.or

//FindDuplicates:Youhaveanarraywithallthenumbersfrom1toN,whereNisatmost32,000.The array may have duplicate entries and you do not know what N is. With only 4 kilobytes of memory available, how would you print all duplicate elements in the array?

fun main() {
    val list = mutableListOf<Int>()
    for (i in 0..20000) {
        list.add(i)
    }
    list.add(45)
    list.add(78)
    list.add(19000)
    val res = findDuplicates(list)
    showIterable(res.iterator())
}

fun findDuplicates(list: List<Int>): Set<Int> {
    val bytes = ByteArray(4096)
    val duplicates = mutableSetOf<Int>()
    for (i in list) {
        val pos = i / 8
        val bytePos = i % 8
        val byte = bytes[pos]
        val mask = (1 shl (7 - bytePos)).toByte()
        if (byte and mask != 0.toByte()) {
            duplicates.add(i)
        }
        bytes[pos] = byte or mask
    }
    return duplicates
}