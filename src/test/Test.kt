package test

import tasks.sout
import java.util.*

fun main() {
    val s1 = SomeData(2)
    val s2 = SomeData(2)
    println(s1 == s2)
    println(s1 === s2)
}

data class SomeData(val id: Int)