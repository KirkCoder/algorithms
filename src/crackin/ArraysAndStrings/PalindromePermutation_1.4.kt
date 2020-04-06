package crackin.ArraysAndStrings

//Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.

//Input: Tact Coa
//Output: True (permutations:"taco cat'; "atco cta '; etc.)

fun main() {
//    val input = "tac6tco8a68"
    val input = "Tact Coa"
    println(isPalindromePermutation(input))
}

// only ACSII
private fun isPalindromePermutation(input: String): Boolean {
    var bits = 0
    val aPos = 'a'.toInt()
    val zPos = 'z'.toInt()
    for (c in input) {
        val pos = c.toLowerCase().toInt()
        if (pos in aPos..zPos) {
            val index = pos - aPos
            val mask = 1 shl index
            bits = bits xor mask
        }
    }
    return bits == 0 || (bits and (bits - 1) == 0)
}

// N
private fun isPalindromePermutation1(input: String): Boolean {
    val count = CharArray(Char.MAX_VALUE.toInt())
    var oddCount = 0
    for (c in input) {
        count[c.toInt()]++
        if (count[c.toInt()].toInt() % 2 > 0) {
            oddCount++
        } else {
            oddCount--
        }
    }
    return oddCount < 2
}

// N*logN
private fun isPalindromePermutation2(input: String): Boolean {
    val chars = input.toCharArray()
    chars.sort()
    var prev = chars[0]
    var nextStep = false
    var oddFound = false
    for (j in 1 until chars.size) {
        if (nextStep) {
            prev = chars[j]
            nextStep = false
        } else {
            if (chars[j] == prev) {
                nextStep = true
            } else {
                if (oddFound) {
                    return false
                } else {
                    oddFound = true
                    prev = chars[j]
                }
            }
        }
    }
    if (!nextStep) return false
    return true
}