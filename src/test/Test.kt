package test

import java.util.regex.Matcher
import java.util.regex.Pattern

fun main() {
    val str = "hoho\n" +
            "hihi\n" +
            "haha\n" +
            "hede"

    val pattern: Pattern = Pattern.compile("/^((?!hede).)*\$/s")
    val matcher: Matcher = pattern.matcher(str)
    while (matcher.find()) {
        print(str.substring(matcher.start(), matcher.end()))
    }

    val reg = Regex("^((?!hede).)*\$")
    val resss = str.replace(reg, "")
    println(resss)
}