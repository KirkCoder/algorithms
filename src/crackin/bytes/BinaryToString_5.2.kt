package crackin.bytes

import java.lang.StringBuilder

//Binary to String: Given a real number between 8 and 1 (e.g., 0.72) that is passed in as a double, print the binary representation. If the number cannot be represented accurately in binary with at most 32 characters, print "ERROR:'

fun main() {
    println((0.72).toRawBits().toString(2))
    println(doubleToBitString(0.72))
}

fun doubleToBitString(double: Double): String {
    if (double >= 1 || double < 0) return ""

    var next = double * 2
    val res = StringBuilder()
    while (next != 0.0) {
        if (next >= 1) {
            res.append("1")
            next -= 1
        } else {
            res.append("0")
        }
        next *= 2
    }
    return res.toString()
}