package crackin.ArraysAndStrings

import java.lang.StringBuilder

//URLify: Write a method to replace all spaces in a string with '%2e: You may assume that the string has sufficient space at the end to hold the additional characters, and that you are given the "true" length of the string. (Note: if implementing in Java, please use a character array so that you can perform this operation in place.)
//SOLUTION
//EXAMPLE
//Input: "Mr John Smith    ", 13 Output: "Mr%20John%20Smith"

fun main() {
    val input = "Mr John Smith    "
    println(replaceSpaces(input.toCharArray()))
    println(urlifi(input, input.trim().length))
}

fun replaceSpaces(input: CharArray): CharArray {
    val replacement = "%20".toCharArray()
    val space = ' '
    var firstCharFound = false
    var position = input.size - 1
    var i = input.size - 1
    while (i > 0) {

        if (!firstCharFound) {
            if (input[i] != space) {
                firstCharFound = true
            } else {
                i--
            }
        }

        if (firstCharFound) {
            if (input[i] != space) {
                input[position--] = input[i--]
            } else {
                input[position--] = replacement[2]
                input[position--] = replacement[1]
                input[position--] = replacement[0]
                i--
            }
        }
    }

    return input
}

private const val SPACE_REPLACER = "%20"
private const val SPACE = ' '

private fun urlifi(input: String, length: Int): String {
    val chars = input.toCharArray()
    var spaces = 0
    for (i in 0 until length) {
        if (chars[i] == SPACE) {
            spaces++
        }
    }

    var j = input.length - 1
    for (i in length - 1 downTo 0) {
        if (chars[i] == SPACE) {
            chars[j--] = '0'
            chars[j--] = '2'
            chars[j--] = '%'
        } else {
            chars[j--] = chars[i]
        }
    }
    return chars.joinToString("")
}

// N
private fun urlifi2(input: String): String {
    val validInput = input.trim()
    val res = StringBuilder(validInput.length)
    for (c in validInput) {
        if (c == SPACE) {
            res.append(SPACE_REPLACER)
        } else {
            res.append(c)
        }
    }
    return res.toString()
}