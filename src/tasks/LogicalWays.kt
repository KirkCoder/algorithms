package tasks

fun main() {
    val str = "0^0&0^1|1"
    println(countLogicalWays(str, true))
}

fun countLogicalWays(str: String, logic: Boolean): Int {
    if (str.isEmpty()) return 0
    if (str.length == 1) return if (str.toBool() == logic) 1 else 0

    var ways = 0
    for (i in 1 until str.length step 2) {
        val c = str[i]
        val left = str.substring(0, i)
        val right = str.substring(i + 1, str.length)

        val leftTrue = countLogicalWays(left, true)
        val rightTrue = countLogicalWays(right, true)
        val leftFalse = countLogicalWays(left, false)
        val rightFalse = countLogicalWays(left, false)

        val total = (leftTrue + leftFalse) * (rightTrue + rightFalse)

        val totalTrue = when (c) {
            '^' -> leftTrue * rightFalse + leftFalse * rightTrue
            '&' -> leftTrue * rightTrue
            else -> leftTrue * rightTrue + leftFalse * rightTrue + rightFalse * leftTrue
        }

        ways += if (logic) totalTrue else total - totalTrue
    }
    return ways
}

private fun String.toBool() = this == "1"