package tasks

import java.lang.StringBuilder

fun main() {
    println(check("catcatgocatgo", "bbaba"))
    println(checkSmart("catcatgocatgo", "bbaba"))
}

fun checkSmart(str: String, pattern: String): Boolean {
    if (pattern.isEmpty() && str.isEmpty()) return false
    if (pattern.isEmpty()) return false
    if (str.isEmpty()) return false

    val main = pattern[0]
    val mainCount = countMainPattern(pattern, main)
    val mainMaxLength = str.length / mainCount

    for (i in 1..mainMaxLength) {
        val start = str.substring(0, i)
        val altLength = (str.length - i * mainCount) / (pattern.length - mainCount)
        if (checkString(str, start, altLength, pattern, main)) return true
    }

    return false
}

fun checkString(
    str: String,
    start: String,
    altLength: Int,
    pattern: String,
    main: Char
): Boolean {
    var prev = 0
    if (start == "cat"){
        var t = 0
    }
    for (c in pattern) {
        if (c == main) {
            val tmp = str.substring(prev, prev + start.length)
            prev += start.length
            if (tmp != start) return false
        } else {
            prev += altLength
        }
    }
    return true
}

fun countMainPattern(pattern: String, main: Char): Int {
    var count = 0
    for (c in pattern) {
        if (c == main) count++
    }
    return count
}

fun check(str: String, pattern: String): Boolean {
    var cand = ""
    for (mainSize in 0 until str.length) {
        val main = str.substring(0, mainSize)
        for (j in mainSize..str.length) {
            for (z in j..str.length) {
                val altr = str.substring(j, z)
                cand = checkPattern(pattern, main, altr)
                if (cand == str) {
                    return true
                }
            }
        }
    }
    return false
}

fun checkPattern(pattern: String, main: String, altr: String): String {
    val sb = StringBuilder()
    val first = pattern[0]

    for (c in pattern.toCharArray()) {
        if (c == first) {
            sb.append(main)
        } else {
            sb.append(altr)
        }
    }
    return sb.toString()
}
