package crackin.Recursions

import showIterable
import showList

//Parens: Implement an algorithm to print all valid (Le., properly opened and closed) combinations of n pairs of parentheses.
//EXAMPLE
//Input: 3
//Output:«())),«()()), «() ()J
//() ( () ) J
//() () ()

fun main() {
    val res = getParens(4).toSet().toList().sorted()
    showIterable(res.iterator())
    val res2 = getSequenceSmart(4).sorted()
    showList(res2)
}

fun getSequenceSmart(pairCount: Int): List<String> {
    val list = mutableListOf<String>()
    getSequencesSmart(list, pairCount, pairCount, "")
    return list
}

fun getSequencesSmart(sequences: MutableList<String>, left: Int, right: Int, sequence: String) {
    when {
        left == 0 && right == 0 -> sequences.add(sequence)
        left == right -> {
            getSequencesSmart(sequences, left - 1, right, "$sequence(")
        }
        right > left -> {
            getSequencesSmart(sequences, left, right - 1, "$sequence)")
            if (left > 0) {
                getSequencesSmart(sequences, left - 1, right, "$sequence(")
            }
        }
    }
}

fun getParens(pairCount: Int): List<String> {
    if (pairCount == 1) {
        return listOf("()")
    }

    val parns = getParens(pairCount - 1)
    val newPrans = "()"
    val res = mutableListOf<String>()
    for (parn in parns) {
        for (i in 0..parn.length) {
            val left = parn.substring(0, i)
            val right = parn.substring(i, parn.length)
            res.add(left + newPrans + right)
        }
    }
    return res
}