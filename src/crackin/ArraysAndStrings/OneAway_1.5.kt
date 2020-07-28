package crackin.ArraysAndStrings

import kotlin.math.abs

//One Away: There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character. Given two strings, write a function to check if they are one edit (or zero edits) away.

fun main() {
    println(isOnlyOneDiff("pale", "ple"))
    println(isOnlyOneDiff("pales", "pale"))
    println(isOnlyOneDiff("pale", "bale"))
    println(isOnlyOneDiff("pale", "bae"))
}

fun isOnlyOneDiff(first: String, second: String): Boolean {
    val short: String
    val long: String

    if (first.length < second.length) {
        short = first
        long = second
    } else {
        short = second
        long = first
    }

    if (long.length - short.length > 1) return false

    var s = 0
    var l = 0
    var diffFound = false

    while (s < short.length && l < long.length) {
        if (short[s++] != long[l++]) {
            if (diffFound) {
                return false
            } else {
                diffFound = true
                if (long.length != short.length) l++
            }
        }
    }
    return true
}

fun findEdits(first: String, second: String): Boolean {
    var equalsLength = false
    val short: String
    val long: String
    if (first.length == second.length) {
        short = first
        long = second
        equalsLength = true
    } else {
        if (first.length > second.length) {
            short = second
            long = first
        } else {
            short = first
            long = second
        }
        if (long.length - short.length > 1) return false
    }

    var s = 0
    var l = 0
    var diffFound = false

    while (s < short.length && l < long.length) {
        if (short[s] == long[l]) {
            s++
            l++
        } else {
            if (diffFound) {
                return false
            } else {
                diffFound = true
                if (equalsLength) {
                    s++
                    l++
                } else {
                    l++
                }
            }
        }
    }
    return true
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