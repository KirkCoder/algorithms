package crackin.ArraysAndStrings

//String Rotation: Assume you have a method i 5 S u b s t r i n g which checks ifone word is a substring of another. Given two strings, 51 and 52, write code to check if 52 is a rotation of 51 using only one call to isSubstring (e.g., Uwaterbottleu is a rotation of uerbottlewatU).

fun main() {
    println(isSubString3("waterbottle", "erbottlewat"))
}

fun isSubString3(first: String, second: String): Boolean {
    val connect = first + first
    for (i in 0 until connect.length) {
        if (connect[i] == second[0] && checkAll(connect, i, second)) {
            return true
        }
    }
    return false
}

fun checkAll(connect: String, index: Int, second: String): Boolean {
    var j = 0
    for(i in index until index + second.length) {
        if(connect[i] != second[j++]) {
            return false
        }
    }
    return true
}

private fun isSubstring(s1: String, s2: String): Boolean {
    if (s1.length != s2.length || s1.isEmpty()) return false
    val concat = (s1 + s1).toCharArray()
    val c2 = s2.toCharArray()

    for (i in 0..concat.size - c2.size) {
        if (concat[i] == c2[0]) {
            if (checkString(concat, i, c2)) return true
        }
    }
    return false
}

private fun checkString(concat: CharArray, i: Int, c2: CharArray): Boolean {
    for ((k, j) in (i until (i + c2.size)).withIndex()) {
        if (concat[j] != c2[k]) {
            return false
        }
    }
    return true
}