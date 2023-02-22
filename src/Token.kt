import java.io.File

fun main() {
    val list = listOf("11111", "100", "1101", "1101", "11000")
    println(findMaxForm(list.toTypedArray(), 5, 7))
    println(findMaxForm1(list.toTypedArray(), 5, 7))
}

fun findMaxForm1(strs: Array<String>, m: Int, n: Int): Int {
    val matrix = Array<IntArray>(m + 1){ IntArray(n + 1) }

    var max = 0
    for(s in strs) {
        val (o, z) = calc(s)
        for(zeros in m downTo z) {
            for(ones in n downTo o) {
                matrix[zeros][ones] = Math.max(matrix[zeros][ones], matrix[zeros - z][ones - o] + 1)
            }
        }
    }

    return matrix[m][n]
}

private fun calc(s: String): Pair<Int, Int> {
    var o = 0
    var z = 0

    for(c in s) {
        if(c == '1') {
            o++
        } else {
            z++
        }
    }

    return Pair(o, z)
}

fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
    val dp = Array(m + 1) { IntArray(n + 1) }
    for (s in strs) {
        val (o, z) = calc(s)
        for (zeroes in m downTo z) {
            for (ones in n downTo o) {
                dp[zeroes][ones] = Math.max(
                    1 + dp[zeroes - z][ones - o],
                    dp[zeroes][ones]
                )
            }
        }
    }
    return dp[m][n]
}

fun countzeroesones(s: String): IntArray {
    val c = IntArray(2)
    for (i in 0 until s.length) {
        c[s[i] - '0']++
    }
    return c
}