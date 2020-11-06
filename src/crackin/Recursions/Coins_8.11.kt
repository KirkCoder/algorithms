package crackin.Recursions

//Coins: Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and
//pennies (1 cent), write code to calculate the number of ways of representing n cents.

fun main() {
    println(countCoins(10))
    println(makeChange(10))
}

fun countCoins(cents: Int): Int {
    var count = 0
    count += countCoins(cents, 0, 1)
    count += countCoins(cents, 0, 5)
    count += countCoins(cents, 0, 10)
    count += countCoins(cents, 0, 25)
    return count
}

private fun countCoins(cents: Int, current: Int, coin: Int): Int {
    if(current + coin > cents) return 0
    if(current + coin == cents) return 1

    var count = 0
    count += countCoins(cents, current + coin, 1)
    count += countCoins(cents, current + coin, 5)
    count += countCoins(cents, current + coin, 10)
    count += countCoins(cents, current + coin, 25)
    return count
}

fun makeChange(n: Int): Int {
    val arr = intArrayOf(25, 10, 5, 1)
    return makeChange(n, arr, 0)
}

fun makeChange(amount: Int, denoms: IntArray, index: Int): Int {
    if (index >= denoms.size - 1) return 1
    val denomAmount = denoms[index];
    var ways = 0;
    var i = 0
    while (i * denomAmount <= amount) {
        val amountRemaining = amount - i * denomAmount
        ways += makeChange(amountRemaining, denoms, index + 1)
        i++
    }
    return ways
}