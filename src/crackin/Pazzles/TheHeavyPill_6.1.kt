package crackin.Pazzles

fun main() {
    val bottles = mutableListOf<Bottle>()
    for (i in 0 until 20) {
        if (i == 13) {
            bottles.add(Bottle(1.1))
        } else {
            bottles.add(Bottle(1.0))
        }
    }
    println(findBottleWithExtraPills(bottles))
}

private fun findBottleWithExtraPills(bottles: List<Bottle>): Double {
    var count = 1
    var weight = 0.0
    for (bottle in bottles) {
        for (i in 0 until count) {
            weight += bottle.pills.first().wight
        }
        count++
    }
    return (weight - 210) / 0.1
}

class Pill(val wight: Double)
class Bottle(private val pillWeight: Double, val size: Int = 20) {
    val pills: List<Pill>

    init {
        val pills = mutableListOf<Pill>()
        for (i in 0..size) {
            pills.add(Pill(pillWeight))
        }
        this.pills = pills
    }
}