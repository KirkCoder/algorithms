package crackin.ArraysAndStrings

import kotlin.math.abs

//One Away: There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character. Given two strings, write a function to check if they are one edit (or zero edits) away.

fun main() {
    println(isOneAway("pale", "ple"))
    println(isOneAway("pales", "pale"))
    println(isOneAway("pale", "bale"))
    println(isOneAway("pale", "bae"))
}

private fun isOneAway(first: String, second: String): Boolean {
    val lengthDiff = abs(first.length - second.length)
    if (lengthDiff > 1) {
        return false
    }
    var diffFound = false
    val firstChars = first.toCharArray()
    val secondChars = second.toCharArray()

    val small: CharArray
    val big: CharArray

    if (first.length > second.length) {
        small = secondChars
        big = firstChars
    } else {
        big = secondChars
        small = firstChars
    }

    var s = 0
    var b = 0

    while (s < small.size && b < big.size) {
        if (small[s] != big[b]) {
            if (diffFound) return false
            diffFound = true
            b++
            if (small.size == big.size) {
               s++
            }
        } else {
            b++
            s++
        }
    }

    return true
}