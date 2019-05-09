package tasks

fun main() {
    val str = "0^0&0^1|1"
    val map = mutableMapOf<String, Int>()
    println(countLogicalWays(str, true, map))
}

fun countLogicalWays(
    str: String,
    logic: Boolean,
    map: MutableMap<String, Int>
): Int {
    if (str.isEmpty()) return 0
    if (str.length == 1) return if (str.toBool() == logic) 1 else 0
    map[str + logic]?.let { return it }

    var ways = 0
    for (i in 1 until str.length step 2) {
        val c = str[i]
        val left = str.substring(0, i)
        val right = str.substring(i + 1, str.length)

        val leftTrue = countLogicalWays(left, true, map)
        val rightTrue = countLogicalWays(right, true, map)
        val leftFalse = countLogicalWays(left, false, map)
        val rightFalse = countLogicalWays(left, false, map)

        val total = (leftTrue + leftFalse) * (rightTrue + rightFalse)

        val totalTrue = when (c) {
            '^' -> leftTrue * rightFalse + leftFalse * rightTrue
            '&' -> leftTrue * rightTrue
            else -> leftTrue * rightTrue + leftFalse * rightTrue + rightFalse * leftTrue
        }

        ways += if (logic) totalTrue else total - totalTrue
        map[str + logic] = ways
    }
    return ways
}

private fun String.toBool() = this == "1"