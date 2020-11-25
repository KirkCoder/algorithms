package leetcode

//Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
//
//If possible, output any possible result.  If not possible, return the empty string.
//
//Example 1:
//
//Input: S = "aab"
//Output: "aba"
//Example 2:
//
//Input: S = "aaab"
//Output: ""


fun reorganizeString(S: String): String {
    if(S.isEmpty()) return ""
    val chars = S.toCharArray()
    val map = mutableMapOf<Char, Int>()
    for(c in chars) {
        if(map[c] == null) {
            map[c] = 1
        } else {
            map[c] = map[c]!! + 1
        }
    }

    var i = 1
    var count = 0
    var c = 's'

    for (entry in map) {
        if(entry.value > count) {
            count = entry.value
            c = entry.key
        }
    }
    map[c] = map[c]!! - 1
    chars[0] = c
    while (i < chars.size) {
        val newC = getMax(map, c)
        if(newC == null) {
            return ""
        }
        c = newC
        chars[i++] = c
    }
    return chars.joinToString("")
}

fun getMax(map: MutableMap<Char, Int>, current: Char): Char? {
    var count = 0
    var c: Char? = null
    for(entry in map) {
        if(entry.value > count && entry.key != current) {
            count = entry.value
            c = entry.key
        }
    }
    if(c != null) {
        map[c] = map[c]!! - 1
    }
    return c
}