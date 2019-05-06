fun main() {
    generateSequences(listOf("aaa", "bbb", "ccc", "ddd", "eee"), 0).forEach {
        for (s in it) {
            print("$s, ")
        }
        println()
    }
}

fun generateSequences(array: List<String>, position: Int): MutableList<MutableList<String>> {

    if (position >= array.size) {
        return mutableListOf<MutableList<String>>()
            .apply { add(mutableListOf()) }
    }

    val sequences = generateSequences(array, position + 1)
    val first = array[position]
    val tmpSequence = mutableListOf<MutableList<String>>()
    for (sequence in sequences) {
        val newSequence = mutableListOf<String>()
        newSequence.addAll(sequence)
        newSequence.add(first)
        tmpSequence.add(newSequence)
    }
    sequences.addAll(tmpSequence)
    return sequences
}

