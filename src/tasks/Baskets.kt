package tasks

fun main() {
    val pairs = 3
    val length = pairs * 2
    val sequence = mutableListOf<String>()
    val chars = arrayOfNulls<Char>(length)
    generate(pairs, pairs, sequence, chars, 0)
    for (s in sequence) {
        println(s)
    }
}

fun generate(
    open: Int,
    close: Int,
    sequence: MutableList<String>,
    chars: Array<Char?>,
    position: Int
) {
    if (open < 0 || close < open) return
    if (open == 0 && close == 0){
        sequence.add(chars.joinToString(""))
    }
    if (open > 0){
        chars[position] = OPEN
        generate(open - 1, close, sequence, chars, position + 1)
    }

    if (close > open){
        chars[position] = CLOSE
        generate(open, close - 1, sequence, chars, position + 1)
    }
}

private const val OPEN = '('
private const val CLOSE = ')'