package olump

import java.io.*
import java.nio.charset.Charset
import kotlin.math.pow

fun main() {
    var line = 0
    var n: Int = -1
    var k: Int = -1
    var m: Int = -1
    File("src/olump/kabel").bufferedReader().useLines { seq ->
        for (s in seq) {
            val filtered = s.toCharArray().filter { it != ' ' }
            when {
                line == 0 -> {
                    n = filtered[0].toString().toInt()
                    k = filtered[1].toString().toInt()
                    m = filtered[2].toString().toInt()
                    line++
                }
                k > 0 -> {

                }
            }
        }
    }
}



//fun main() {
//    var line = 0
//    File("src/olump/kabel").bufferedReader().use {
//        var res = it.read()
//        var n = -1
//        var k = -1
//        var m = -1
//        while (res != -1) {
//            val c = res.toChar()
//            if (c != '\n' && c != ' ') {
//                when {
//                    line == 0 && n == -1 -> {
//                        n = c.toString().toInt()
//                    }
//                    line == 0 && k == -1 -> {
//                        k = c.toString().toInt()
//                    }
//                    line == 0 && m == -1 -> {
//                        m = c.toString().toInt()
//                    }
//                    k > 0 -> {
//
//                    }
//                }
//            }
//            if (c == ' ') {
//                line++
//            }
//            res = it.read()
//        }
//    }
//}


//fun main() {
//    File("src/olump/kabel").bufferedReader().use {
//        var res = it.read()
//        while (res != -1) {
//            val c = res.toChar()
//            if (c == '\n') println("ret")
//            if (c == ' ') println("space")
//            res = it.read()
//        }
//    }
//}