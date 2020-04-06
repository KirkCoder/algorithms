package crackin.ArraysAndStrings

import java.lang.StringBuilder

//String Compression: Implement a method to perform basic string compression using the counts of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the "compressed"string would not become smaller than the original string, your method should return the original string. You can assume the string has only uppercase and lowercase letters (a - z).

fun main() {
    val input = "aabccccctaaa"
    println(compress(input))
}

private fun compress(input: String): String {
    val chars = input.toCharArray()
    val result = StringBuilder()
    var prev = chars[0]
    var count = 1

    for (i in 1 until input.length) {
        if (prev == input[i]) {
            count++
        } else {
            appendResult(count, result, prev)
            prev = input[i]
            count = 1
        }
    }
    appendResult(count, result, prev)

    return result.toString()
}

private fun appendResult(count: Int, result: StringBuilder, prev: Char) {
    if (count == 1) {
        result.append(prev)
    } else {
        result.append(prev)
        result.append(count)
    }
}