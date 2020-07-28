package test

fun main() {
    println(isPalindrom("Tact Coa"))
}

fun isPalindrom(str: String): Boolean {
    val mask = IntArray(Char.MAX_VALUE.toInt())
    var oddCount = 0
    for(c in str.toLowerCase()) {
        if (c == ' ') continue
        if(mask[c.toInt()] % 2 == 0) {
            oddCount++
            mask[c.toInt()]++
        } else {
            mask[c.toInt()]++
            oddCount--
        }
    }
    return oddCount < 2
}