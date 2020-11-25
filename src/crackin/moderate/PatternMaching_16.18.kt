package crackin.moderate

//Pattern Matching:You are given two strings, pattern and value. The pattern string consists of justthelettersaandb,describingapatternwithinastring.Forexample,thestring catcatgocatgo matchesthe pattern aabab (where cat is a and go is b). It also matches patterns like a, ab, and b. Write amethod to determine ifvalue matches pattern.

fun main() {
//    val pattern = "aabab"
    val pattern = "abb"
    val str = "catcatgocatgo"
    println(isMachPattern(pattern, str))
}

fun isMachPattern(pattern: String, str: String): Boolean {
    val aCount = pattern.count { it == 'a' }
    val bCount = pattern.length - aCount
    if (aCount == 0 || bCount == 0) return true
    var pair: Pair<Int, Int>? = getPatternSizes(str.length, 1, aCount, bCount) ?: return false
    while (pair != null) {
        var a: String? = null
        var b: String? = null
        var c = 0
        var i = 0
        var isWrong = false
        while (i < pattern.length && !isWrong) {
            if (pattern[i] == 'a') {
                val tmpA = str.substring(c, c + pair.first)
                c += pair.first
                when {
                    a == null -> {
                        a = tmpA
                    }
                    a != tmpA -> {
                        isWrong = true
                    }
                }
            } else {
                val tmpB = str.substring(c, c + pair.second)
                c += pair.second
                when {
                    b == null -> {
                        b = tmpB
                    }
                    b != tmpB -> {
                        isWrong = true
                    }
                }
            }
            i++
        }
        if (!isWrong) return true
        pair = getPatternSizes(str.length, pair.first + 1, aCount, bCount)
    }
    return false
}


fun getPatternSizes(length: Int, aSize: Int, aCount: Int, bCount: Int): Pair<Int, Int>? {
    var a = aSize
    var extraLength = length - a * aCount
    if (extraLength <= 0) return null
    var b = if (extraLength % bCount == 0) {
        extraLength / bCount
    } else {
        -1
    }
    while (b == -1) {
        a++
        extraLength = length - a * aCount
        if (extraLength <= 0) return null
        b = if (extraLength % bCount == 0) {
            extraLength / bCount
        } else {
            -1
        }
    }
    return Pair(a, b)
}