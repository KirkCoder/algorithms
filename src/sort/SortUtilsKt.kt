package sort

fun <T> showArray(array: Array<T>) {
    for (i in array) {
        print("$i, ")
    }
    println()
}

fun generateArray(length: Int = 32) = Array(length) { (Math.random() * 99).toInt() }