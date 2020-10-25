package crackin.bytes

//Insertion: You are given two 32-bit numbers, Nand M, and two bit positions, i and j. Write a method to insert Minto N such that M starts at bit j and ends at bit i. You can assume that the bits j through i have enough space to fit all of M. That is, if M= 10011, you can assume that there are at least 5 bits between j and i. You would not, for example, have j = 3 and i = 2, because Mcould not fully fit between bit 3 and bit 2.

fun main() {
//    val to = 0b10000000000
//    val to = 0b11101010100101111111111111
    val to = 0b1000000000001010
    val from = 0b10011
    val i = 2
    val j = 6
    println(to.toString(2))
    println(insert(from, to, j, i).toString(2))
}

fun insert(from: Int, to: Int, j: Int, i: Int): Int {

    val clearMask = (-1 shl (j + 1)) or (Int.MAX_VALUE shr (31 - i))
    return (to and clearMask) or (from shl i)
}