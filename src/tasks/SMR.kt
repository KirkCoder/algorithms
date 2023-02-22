package test

import java.io.File

//36
//91
//127

//fun main() {
//showArray(generateArray())
//}

fun main() {
    val list = File("input.txt").readLines()[1].split(" ").map { it.toInt() }

    var zeros = 0
    for (i in list.indices) {
        if (list[i] == 0) {
            zeros++
        }
    }

    if (zeros > 1 || list.size == 1) {
        File("output.txt").writeText(list[0].toString())
        return
    }

    var total = 1

    for (n in list) {
        if (n != 0) {
            total *= n
        }
    }

    if (zeros > 0) {
        if (total < 0) {
            File("output.txt").writeText((list.firstOrNull{ it != 0 } ?: 0).toString())
        } else {
            File("output.txt").writeText("0")
        }
        return
    }

    var diff = total / list[0]
    var res = list[0]

    for (i in 1 until list.size) {
        val n = total / list[i]
        if (n > diff) {
            diff = n
            res = list[i]
        }
    }

    File("output.txt").writeText(res.toString())
}

//fun main() {
//    val list = File("input.txt").readLines()[1].split(" ")
////    val list = listOf(10, 9, 8, 7)
////    val list = listOf(1, 2, 3, 4, 5)
//    var min = list[0].toInt()
//    var diff = 0
//    for(i in 1 until list.size) {
//        val n = list[i].toInt()
//        if(min < n) {
//            diff += n - min
//            min = n
//        } else if(n < min) {
//            min = n
//        }
//    }
//
////    File("output.txt").writeText(diff.toString())
//    println(diff)
//}


//fun main() {
//    val list = File("input.txt").readLines()
//    if (list.size < 2) return
//    val cleanLogins = mutableSetOf<String>()
//    for (i in 1 until list.size) {
//        val loginDomain = list[i].split("@")
//        val login = loginDomain[0].split("-")[0]
//        val sb = StringBuilder()
//        for (c in login) {
//            if (c !='.') {
//                sb.append(c)
//            }
//        }
//        sb.append("@")
//        val domain = loginDomain[1].split('.')
//        for (j in 0 until domain.size - 1) {
//            sb.append(domain[j])
//            sb.append('.')
//        }
//        cleanLogins.add(sb.toString())
//    }
//
//
//    File("output.txt").writeText(cleanLogins.size.toString())
//}



//fun main() {
//    val list = File("input.txt").readLines()
//    if (list.size < 3) return
//    val n = list[0].toInt()
//    val s = list[1]
//    val arr = list[2].split(" ").map { it.toInt() }
//    if (arr.isEmpty() || s.isEmpty()) return
//    var max = arr[0]
//    var maxChar = s[0]
//
//    for (i in 1 until arr.size) {
//        val diff = arr[i] - arr[i - 1]
//        if (diff >= max) {
//            max = diff
//            maxChar = s[i]
//        }
//    }
//
//    File("output.txt").writeText(maxChar.toString())
//}