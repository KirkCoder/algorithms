package crackin.Pazzles

import showList

fun main() {
    val res = findAllPrimes(30)
    showList(res)
}

fun findAllPrimes(value: Int): List<Int> {
    if (value < 2) return emptyList()
    val numbers = BooleanArray(value + 1)
    for (i in 0..value) {
        numbers[i] = true
    }
    numbers[0] = false
    numbers[1] = false
    var pos = 2

    while (pos * pos < numbers.size) {
        remove(pos, numbers)
        pos ++
    }
    val result = mutableListOf<Int>()
    for (number in numbers.withIndex()) {
        if (number.value) {
            result.add(number.index)
        }
    }
    return result
}

private fun remove(next: Int, values: BooleanArray) {
    var tmp = next
    while (tmp + next <= values.size) {
        tmp += next
        values[tmp] = false
    }
}