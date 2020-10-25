package crackin.bytes

//Next Number: Given a positive integer, print the next smallest and the next largest number that have the same number of 1 bits in their binary representation.

fun main() {
//    val value = 0b11011001111100
    val value = 0b10011
    println(value.toString(2))
    val next = getNext(value)
    val prev = getPrev(next)
    println(next.toString(2))
    println(prev.toString(2))
}

fun getNext(value: Int): Int {
    var p = 0
    var ones = 0
    var tmp = value

    while(tmp and 1 == 0) {
        p++
        tmp = tmp shr 1
    }

    while(tmp and 1 == 1) {
        p++
        ones++
        tmp = tmp shr 1
    }

    tmp = value and ((-1) shl p)

    tmp = tmp or (1 shl p)

    var mask = 1
    for(i in 0 until ones - 1) {
        tmp = tmp or mask
        mask = mask shl 1
    }

    return tmp
}

fun getPrev(value: Int): Int {
    var ones = 0
    var p = 0
    var tmp = value
    while(tmp and 1 == 1) {
        ones++
        p++
        tmp = tmp shr 1
    }

    while(tmp and 1 == 0) {
        p++
        tmp = tmp shr 1
    }

    val mask = (-1) shl p + 1
    tmp = value and mask
    var onesMask = 1 shl (p - 1)


    for (i in 0 until ones + 1) {
        tmp = tmp or onesMask
        onesMask = onesMask shr 1
    }

    return tmp
}
















