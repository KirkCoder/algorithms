package tasks

fun main() {
    val steps = 7
    println(countWays(steps))
    val cash = arrayOfNulls<Int>(steps + 1)
    println(countWaySmart(steps, cash))
}

fun countWaySmart(n: Int, cash: Array<Int?>): Int {
    return when {
        n < 0 -> 0
        n == 0 -> 1
        else -> {
            if (cash[n] != null){
                cash[n]!!
            } else {
                val res = countWaySmart(n - 1, cash) + countWaySmart(n - 2, cash) + countWaySmart(n - 3, cash)
                cash[n] = res
                res
            }
        }
    }
}

fun countWays(n: Int): Int {
    return when {
        n < 0 -> 0
        n == 0 -> 1
        else -> countWays(n - 1) + countWays(n - 2) + countWays(n - 3)
    }
}