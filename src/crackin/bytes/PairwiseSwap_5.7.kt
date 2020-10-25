package crackin.bytes


//5.7 Pairwise Swap: Write a program to swap odd and even bits in an integer with as few instructions as possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).

fun main() {
    val value = 0b1010101
//    val value = 0b11110
    println(swapBits(value).toString(2))
}

fun swapBits(value: Int): Int {
    val tmp = value.toLong()
    val mask = 0xaaaaaaaa.toInt()
    val evenMask = mask ushr 1
    return (((value and mask) ushr 1) or ((value and evenMask shl 1))).toInt()
}