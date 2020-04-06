package crackin.pre

// find all permutations in strings
// 82


fun main() {
    val aStr = "abbc".toCharArray()
    val bStr = "cbabadcbbabbcbabaabccbabc".toCharArray()
    var permutations = 0
    for (i in aStr.indices) {
        for (j in bStr.indices) {
            if (aStr[i] == bStr[j]) {
                if (computePermutation(aStr, bStr, i, j)) {
                    permutations++
                }
            }
        }
    }

    println(permutations)
}

private fun computePermutation(aStr: CharArray, bStr: CharArray, a: Int, b: Int): Boolean {
    val positions = BooleanArray(aStr.size)
    positions[a] = true


    var i = b + 1
    val end = b + aStr.size - 1
    if (end >= bStr.size) return false
    while (i <= end) {
        val next = bStr[i]
        var find = false
        for (j in aStr.indices) {
            if (aStr[j] == next && !positions[j] && !find) {
                find = true
                positions[j] = true
            }
        }
        if (!find) {
            return false
        }
        i++
    }
    return true
}