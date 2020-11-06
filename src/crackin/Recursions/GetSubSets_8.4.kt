package crackin.Recursions

import showList

//Power Set: Write a method to return all subsets of a set.

fun main() {
    val res = getSubSets(listOf(1, 2, 3, 4))
    for (list in res) {
        showList(list)
    }
}

fun getSubSets(list: List<Int>): List<List<Int>> {
    val sets = mutableListOf<List<Int>>()
    appendSubSets(list, 0, sets)
    return sets
}

private fun appendSubSets(list: List<Int>, position: Int, sets: MutableList<List<Int>>) {
    if (position > list.size - 1) return
    val next = list[position]
    val i = sets.iterator()
    val newSets = mutableListOf<List<Int>>()
    for (set in sets) {
        val nextSet = i.next().copy()
        nextSet.add(next)
        newSets.add(nextSet)
    }
    newSets.add(listOf(next))
    sets.addAll(newSets)
    appendSubSets(list, position + 1, sets)
}

private fun List<Int>.copy(): MutableList<Int> {
    val res = mutableListOf<Int>()
    for (i in this) {
        res.add(i)
    }
    return res
}