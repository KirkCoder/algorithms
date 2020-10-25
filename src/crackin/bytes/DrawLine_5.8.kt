package crackin.bytes

import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.xor

//Draw Line: A monochrome screen is stored as a single array of bytes, allowing eight consecutive pixels to be stored in one byte.The screen has width w, where wis divisible by 8 (that is, no byte will be split across rows).The height of the screen, ofcourse, can be derived from the length of the array and the width. Implement a function that draws a horizontal line from (xl, y) to (x2, y),
//The method signature should look something like:
//drawLine(byte[] screen, int width, int Xl, int x2, int y)

fun main() {
    val bytes = byteArrayOf(
        0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0
    )
    drawLine(bytes, 6, 10, 33, 2)
    println()
    for (byte in bytes.withIndex()) {
        if (byte.index >= 6 && byte.index % 6 == 0) {
            println()
        }
        print(byte.value.toUByte().toString(2))
        print(", ")
    }
}

fun drawLine(bytes: ByteArray, width: Int, x1: Int, x2: Int, y: Int) {
    var firstBytePos = width * y + x1 / 8
    if (x1 % 8 > 0) {
        firstBytePos++
    }

    var secondBytePos = width * y + x2 / 8
    if (x2 % 8 > 0) {
        secondBytePos++
    }

    val firstByte = bytes[firstBytePos]
    val secondByte = bytes[secondBytePos]

    val x1InByte = x1 % 8
    val x2InByte = x2 % 8

    if (firstBytePos == secondBytePos) {
        val mask = ((-1) shl (8 - x1InByte)).toByte() xor ((1 shl (8 - x2InByte)) - 1).toByte()
        val byte = (firstByte and mask) xor mask.inv()
        bytes[firstBytePos] = byte
    } else {
        val firstMask = ((-1) shl (8 - x1InByte)).toByte()
        val newFirstByte = (firstByte and firstMask) xor firstMask.inv()
        val newSecondByte = if (x2 % 8 == 0) {
            0xff.toByte()
        } else {
            val secondMask = ((1 shl (8 - x2InByte)) - 1).toByte()
            (secondByte and secondMask) xor secondMask.inv()
        }

        bytes[secondBytePos] = newSecondByte
        bytes[firstBytePos] = newFirstByte

        for (p in firstBytePos + 1 until secondBytePos) {
            bytes[p] = 0xff.toByte()
        }
    }
}