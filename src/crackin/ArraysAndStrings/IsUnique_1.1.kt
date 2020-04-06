package crackin.ArraysAndStrings

//Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures?

fun main() {
    println(isAllCharsUnique2("+₽jекуpoiuytr456789"))
}

// N*logN
private fun isAllCharsUnique(str: String): Boolean {
    val chars = str.toCharArray()
    if (chars.size < 2) return true
    chars.sort()
    var prev: Char = chars[0]
    for (i in 1 until chars.size) {
        if (chars[i] == prev) {
            return false
        } else {
            prev = chars[i]
        }
    }
    return true
}

// N
private fun isAllCharsUnique2(str: String): Boolean {
    val checks = BooleanArray(Char.MAX_VALUE.toInt())
    for (c in str) {
        if (checks[c.toInt()]) {
            return false
        } else {
            checks[c.toInt()] = true
        }
    }
    return true
}