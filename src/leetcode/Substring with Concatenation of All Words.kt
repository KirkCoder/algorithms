package leetcode

//You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.
//
//You can return the answer in any order.
//
//
//
//Example 1:
//
//Input: s = "barfoothefoobarman", words = ["foo","bar"]
//Output: [0,9]
//Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
//The output order does not matter, returning [9,0] is fine too.
//Example 2:
//
//Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//Output: []
//Example 3:
//
//Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//Output: [6,9,12]
//
//
//Constraints:

fun findSubstring(s: String, words: Array<String>): List<Int> {
    val map = mutableMapOf<String, Int>()
    for (w in words) {
        if (map[w] == null) {
            map[w] = 1
        } else {
            map[w] = map[w]!! + 1
        }
    }

    val tmpMap = mutableMapOf<String, Int>()

    var totalSize = 0
    for (set in map) {
        tmpMap[set.key] = set.value
        totalSize += set.value
    }

    val wordLength = words[0].length
    val totalLength = wordLength * words.size

    var i = 0
    val res = mutableListOf<Int>()
    while (i <= s.length - totalLength) {
        var j = i
        var currentSize = totalSize
        while (currentSize > 0) {
            val tmpS = s.substring(j, j + wordLength)
            if (tmpMap[tmpS] == null || tmpMap[tmpS] == 0) {
                break
            } else {
                tmpMap[tmpS] = tmpMap[tmpS]!! - 1
                currentSize--
                j += wordLength
            }
        }
        if (currentSize == 0) {
            res.add(i)
        }
        for (set in map) {
            tmpMap[set.key] = set.value
        }
        i++
    }
    return res
}