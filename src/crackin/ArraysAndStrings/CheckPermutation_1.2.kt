package crackin.ArraysAndStrings


//1.2 Check Permutation: Given two strings, write a method to decide if one is a permutation of the other

fun main() {
    println(checkIsPermutation("god", "god"))
    println(checkIsPermutation2("god", "god"))
}

// N*logN
private fun checkIsPermutation(first: String, second: String): Boolean {
    if (first.length != second.length) return false
    val firstChars = first.toCharArray()
    val secondChars = second.toCharArray()

    for (i in first.indices) {
        if (firstChars[i] != secondChars[i]) {
            return false
        }
    }
    return true
}

// N
private fun checkIsPermutation2(first: String, second: String): Boolean {
    if (first.length != second.length) return false
    val array = IntArray(Char.MAX_VALUE.toInt())

    for (c in first) {
        array[c.toInt()]++
    }

    for (c in second) {
        array[c.toInt()]--
        if (array[c.toInt()] < 0) {
            return false
        }
    }
    return true
}