package tasks

import java.nio.charset.StandardCharsets

val base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray()
val base64Stub = '='

fun main() {
    val str = "васо привет good by435 lkdf  testtttt!!!"
    val strBytes = str.toByteArray()
    val base64Result = bytesToBase64(strBytes).joinToString("")
    println(base64Result)
    val base64Bytes = base64Result.toByteArray(StandardCharsets.UTF_8)
    val decodedBase64 = fromBase64(base64Bytes)
    println(String(decodedBase64))
}

fun bytesToBase64(bytes: ByteArray): CharArray {
    val commonSize = bytes.size / 3
    val fullBytesSize = 4 * commonSize
    val overBytesSize = if (bytes.size % 3 == 0) 0 else 4
    val size = fullBytesSize + overBytesSize
    val result = CharArray(size)
    var position = 0
    var resultPosition = 0

    while (position < commonSize * 3) {
        val point = (bytes[position++].toInt() and 0xFF shl 16) or
                (bytes[position++].toInt() and 0xFF shl 8) or
                (bytes[position++].toInt() and 0xFF)

        result[resultPosition++] = base64Chars[((point ushr 18) and 0x3F)]
        result[resultPosition++] = base64Chars[((point ushr 12) and 0x3F)]
        result[resultPosition++] = base64Chars[((point ushr 6) and 0x3F)]
        result[resultPosition++] = base64Chars[(point and 0x3F)]
    }

    if (position < bytes.size) {
        var point = (bytes[position++].toInt() and 0xFF)
        if (position < bytes.size) {
            point = (point shl 8) or (bytes[position].toInt() and 0xFF)
            result[resultPosition++] = base64Chars[(point ushr 10) and 0X3F]
            result[resultPosition++] = base64Chars[(point ushr 4) and 0X3F]
            result[resultPosition++] = base64Chars[(point shl 2) and 0X3F]
            result[resultPosition] = base64Stub
        } else {
            result[resultPosition++] = base64Chars[(point ushr 2) and 0X3F]
            result[resultPosition++] = base64Chars[point and 0X3F]
            result[resultPosition++] = base64Stub
            result[resultPosition] = base64Stub
        }
    }

    return result
}

fun fromBase64(bytes: ByteArray): ByteArray {
    var size = (bytes.size / 4) * 3
    if (bytes[bytes.size - 1].toChar() == base64Stub) {
        size--
    }
    if (bytes[bytes.size - 2].toChar() == base64Stub) {
        size--
    }
    val result = ByteArray(size)
    var position = 0
    var resPosition = 0
    while (resPosition < size) {
        var nextBytes = 0
        nextBytes = if (position < bytes.size - 1) {
            val byte = bytes[position++]
            val element = base64Chars.indexOf(byte.toChar())
            (element and 0x3f) shl 18
        } else {
            nextBytes
        }
        nextBytes = if (position < bytes.size - 1) {
            val byte = bytes[position++]
            val element = base64Chars.indexOf(byte.toChar())
            nextBytes or ((element and 0x3f) shl 12)
        } else {
            nextBytes
        }
        nextBytes = if (position < bytes.size - 1) {
            val byte = bytes[position++]
            val element = base64Chars.indexOf(byte.toChar())
            nextBytes or ((element and 0x3f) shl 6)
        } else {
            nextBytes
        }
        nextBytes = if (position < bytes.size - 1) {
            val byte = bytes[position++]
            val element = base64Chars.indexOf(byte.toChar())
            nextBytes or (element and 0x3f)
        } else {
            nextBytes
        }
        if (resPosition < size) result[resPosition++] = (nextBytes ushr 16).toByte()
        if (resPosition < size) result[resPosition++] = ((nextBytes ushr 8) and 0xFF).toByte()
        if (resPosition < size) result[resPosition++] = (nextBytes and 0xFF).toByte()
    }
    return result
}

fun String.pr() {
    var i = 0
    toCharArray().forEach {
        if (i == 8) {
            i = 1
            print(", $it")
        } else {
            print(it)
            i++
        }
    }
}