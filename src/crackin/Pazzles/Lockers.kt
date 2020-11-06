package crackin.Pazzles

import showIterable

fun main() {
    val lockers = BooleanArray(100) { true }
    for (i in 2..lockers.size) {
        toggleLockers(i, lockers)
    }
    showIterable(lockers.iterator())
}

fun toggleLockers(i: Int, lockers: BooleanArray) {
    var j = i
    while (j <= lockers.size) {
        lockers[j - 1] = !lockers[j - 1]
        j += i
    }
}