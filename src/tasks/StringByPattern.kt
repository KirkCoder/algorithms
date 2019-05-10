package tasks

import java.lang.StringBuilder

fun main() {
    println(check("catcatgocatgo", "aabab"))
}

fun check(str: String, pattern: String): Boolean {

    for (mainSize in 0 until str.length) {
        val main = str.substring(0, mainSize)
        for (j in mainSize..str.length) {
            for (z in j..str.length) {
                val altr = str.substring(j, z)
                val cand = checkPattern(pattern, main, altr)
                if (cand == str) return true
            }
        }
    }
    return false
}

fun checkPattern(pattern: String, main: String, altr: String): String {
    val sb = StringBuilder()
    val first = pattern[0]

    for (c in pattern.toCharArray()){
        if (c == first){
            sb.append(main)
        } else {
            sb.append(altr)
        }
    }
    return sb.toString()
}
