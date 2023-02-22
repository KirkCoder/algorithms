package test

import generateArray
import showArray
import java.io.File
import java.util.*

// 8, 12, 16, 17, 20, 25, 29, 29, 37, 38, 41, 46, 46, 47, 47, 53, 54, 55, 56, 62, 62, 63, 68, 69, 71, 72, 80, 80, 83, 84, 93, 93,
fun main() {
    val set = TreeSet<Int>(){ p1, p2 ->
        p1.compareTo(p2)
    }
    println(set.ceiling(1)?.toString())
    println(set.ceiling(2)?.toString())
}
//
//class MyCalendar() {
//
//    private val set = TreeSet<Time>()
//
//    fun book(start: Int, end: Int): Boolean {
//        val t = Time(start)
//        val r = set.ceiling()
//        set.floor()
//        return r == Time(4)
//    }
//
//    data class Time(val start: Int) {
//        var end: Int = 0
//    }
//}
