package crackin.Recursions

import showList


//8.7 Permutations without Dups: Write a method to compute all permutations of a string of unique chara c t ers .

fun main() {
//    val permutations = mutableListOf<List<Char>>()
//    computePermutations("abc", permutations)
//    showList(permutations)
    showList(getPermutations("abcd"))
    showList(computePermutations("abcd"))
}

fun computePermutations(str: String): List<String> {
    val permutations = mutableListOf<String>()
    computePermutations("", str, permutations)
    return permutations
}

fun computePermutations(pref: String, str: String, permutations: MutableList<String>) {
    if (str.isEmpty()) {
        permutations.add(pref)
    }

    for (i in str.indices) {
        computePermutations(pref + str[i], str.substring(0, i) + str.substring(i + 1, str.length), permutations)
    }
}

fun getPermutations(str: String): List<String> {
    val permutations = mutableListOf<String>()
    if (str.isEmpty()) {
        permutations.add("")
        return permutations
    }

    val first = str[0]
    val remainder = str.substring(1)
    val perms = getPermutations(remainder)
    for (perm in perms) {
        for (i in 0..perm.length) {
            permutations.add(insertChar(first, perm, i))
        }
    }
    return permutations
}

fun insertChar(char: Char, word: String, position: Int): String {
    val first = word.substring(0, position)
    val second = word.substring(position, word.length)
    return first + char + second
}

fun computePermutations(str: String, permutations: MutableList<List<Char>>) {
    val chars = str.toCharArray()
    for (char in chars) {
        computePermutations(char, permutations)
    }
}

fun computePermutations(char: Char, permutations: MutableList<List<Char>>) {
    val results = mutableListOf<List<Char>>()
    for (permutation in permutations) {
        var p = 1
        while (p < permutation.size) {
            val res = mutableListOf<Char>()
            for (c in permutation.withIndex()) {
                if (p == c.index) {
                    res.add(char)
                }
                res.add(c.value)
            }
            results.add(res)
            p++
        }
        val left = mutableListOf<Char>()
        left.add(char)
        left.addAll(permutation)
        results.add(left)
        val right = mutableListOf<Char>()
        right.addAll(permutation)
        right.add(char)
        results.add(right)
    }
    permutations.add(listOf(char))
    permutations.addAll(results)
}