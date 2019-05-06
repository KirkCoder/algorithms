fun main() {
    println(coinCount(100, arrayOf(25, 10, 5, 1), 0))
}

fun coinCount(amount: Int, coins: Array<Int>, index: Int): Int {
    if (index >= coins.size - 1) return 1
    val coin = coins[index]
    var tmpValue = 0
    var i = 0
    var count = 0
    while (tmpValue <= amount){
        tmpValue = i * coin
        i++
        count += coinCount(amount - tmpValue, coins, index + 1)
    }
    return count
}