package tasks

fun main() {
    val list = listOf(
        Pair(65, 100),
        Pair(70, 150),
        Pair(56, 90),
        Pair(75, 190),
        Pair(60, 95),
        Pair(68, 110)
    )
    val res = getSequence(list)

    for (re in res) {
        println("${re.first}, ${re.second}")
    }
}

fun getSequence(list: List<Pair<Int, Int>>): List<Pair<Int, Int>> {

    val sorted = list.sortedByDescending { it.second }

    val solutions = mutableListOf<List<Pair<Int, Int>>>()

    for (i in 0 until sorted.size) {
        val pair = sorted[i]
        solutions.addAll(bestSolution(pair, solutions))
    }

    var max = emptyList<Pair<Int, Int>>()

    for (solution in solutions) {
        if (solution.size > max.size) max = solution
    }

    return max
}

fun bestSolution(pair: Pair<Int, Int>, solutions: List<List<Pair<Int, Int>>>): List<List<Pair<Int, Int>>> {
    val result = mutableListOf<List<Pair<Int, Int>>>()
    if (solutions.isEmpty()) {
        val clone = mutableListOf<Pair<Int, Int>>()
        clone.add(pair)
        result.add(clone)
    } else {
        for (solution in solutions) {
            if (canAppend(pair, solution)) {
                val clone = mutableListOf<Pair<Int, Int>>()
                clone.addAll(solution)
                clone.add(pair)
                result.add(clone)
            }
        }
    }
    return result
}


fun canAppend(pair: Pair<Int, Int>, solution: List<Pair<Int, Int>>): Boolean {
    if (solution.isNotEmpty() && solution.last().first > pair.first && solution.last().second > pair.second) {
        return true
    }
    if (solution.isEmpty()) return true
    return false
}
