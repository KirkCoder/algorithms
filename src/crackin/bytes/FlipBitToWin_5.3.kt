package crackin.bytes

//5.3
//Flip Bit to Win: You have an integer and you can flip exactly one bit from a 0 to a 1. Write code to find the length of the longest sequence of 1syou could create.
//SOLUTION
//EXAMPLE
//Input: 1775 (or: 11011101111) Output: 8

fun main() {
    println(getMaxBitLength(1775))
}

fun getMaxBitLength(value: Int): Int {
    if (value.inv() == 0) return 32

    var tmp = value

    var maxLength = 0
    var prevLength = 0
    var currentLength = 0
    while (tmp != 0) {
        if ((tmp and 1) == 1) {
            currentLength++
        } else {
            prevLength = if (tmp and 2 == 0) 0 else currentLength
            currentLength = 0
        }

        maxLength = (prevLength + currentLength + 1).coerceAtLeast(maxLength)
        tmp = tmp ushr 1
    }
    return maxLength
}