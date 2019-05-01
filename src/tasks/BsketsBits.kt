package tasks

fun main(args: Array<String>) {
    val pairs = readLine()!!.toInt()
    val mask = 1 shl (pairs * 2 - 1)
    val sequence = mutableListOf<Int>()
    generate(pairs, pairs, sequence, 0, mask)
    for (s in sequence) {
        var tmpMask = 1 shl pairs * 2 - 1
        for (i in 0 until pairs * 2){
            if (s and tmpMask > 0) print('(') else print(')')
            tmpMask = tmpMask shr 1
        }
        println()
    }
}

fun generate(
    open: Int,
    close: Int,
    sequence: MutableList<Int>,
    chars: Int,
    position: Int
) {
    if (open < 0 || close < open) return
    if (open == 0 && close == 0){
        sequence.add(chars)
    }
    if (open > 0){
        generate(open - 1, close, sequence, chars or position, position shr 1)
    }

    if (close > open){
        generate(open, close - 1, sequence, chars, position shr 1)
    }
}