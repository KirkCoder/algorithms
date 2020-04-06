package crackin.pre

// find all a^3 + b^3 = c^3 + d^3
// 80

fun main() {

    val map = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()

    for (a in 0..1000) {
        for (b in 0..1000) {
            val result = quoter(a) + quoter(b)
            addListIfNull(map, result)
            map[result]!!.add(a to b)
        }
    }

    for (list in map.values) {
        for (pair1 in list) {
            for (pair2 in list) {
                println("$pair1 - $pair2")
            }
        }
    }

    println("@@@@@@@@@@@@@@@@@@")

    for (a in 0..1000) {
        for (b in 0..1000) {
            for (c in 0..1000) {
                for (d in 0..1000) {
                    val pair1 = quoter(a) + quoter(b)
                    val pair2 = quoter(c) + quoter(d)
                    if (pair1 == pair2) println("$pair1 - $pair2")
                }
            }
        }
    }

}

private fun addListIfNull(
    map: MutableMap<Int, MutableList<Pair<Int, Int>>>,
    result: Int
) {
    val list = map[result]
    if (list == null) {
        map[result] = mutableListOf()
    }
}

fun quoter(x: Int) = x * x * x