package crackin.Recursions

//Triple Step: A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3 steps at a time. Implement a method to count how many possible ways the child can run up the stairs

fun main() {
    println(countWays(36))
    println(countWaysMemo(36))
}

fun countWaysMemo(steps: Int): Int {
    val memo = IntArray(steps)
    return countWaysMemo(steps, memo)
}

fun countWaysMemo(steps: Int, memo: IntArray): Int {
    if(steps == 0) return 1
    if (steps < 0 ) return 0
    return if (memo[steps - 1] != 0) {
        memo[steps - 1]
    } else {
        val first = countWaysMemo(steps - 1, memo)
        val second = countWaysMemo(steps - 2, memo)
        val third = countWaysMemo(steps - 3, memo)
        memo[steps - 1] = first + second + third
        memo[steps - 1]
    }
}

fun countWays(steps: Int): Int {
    if(steps == 0) return 1
    if (steps < 0 ) return 0
    val first = countWays(steps - 1)
    val second = countWays(steps - 2)
    val third = countWays(steps - 3)
    return first + second + third
}
