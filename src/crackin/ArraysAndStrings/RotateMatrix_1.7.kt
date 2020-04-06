package crackin.ArraysAndStrings

fun main() {
    val matrix = arrayOf(
        arrayOf(1,  2,  3,  4),
        arrayOf(12, 13, 14,  5),
        arrayOf(11, 16, 15,  6),
        arrayOf(10,  9,  8,  7)
    )

//    val matrix = arrayOf(
//        arrayOf(1, 2),
//        arrayOf(3, 4)
//    )
    rotate(matrix)
    showMatrix(matrix)
}

private fun rotate(matrix: Array<Array<Int>>) {




    var offset = 0
    while (offset < matrix.size / 2) {
        val n = matrix.size - 1 - offset
        var step = 0
        while (step < n - offset) {
            val tmp = matrix[offset + step][offset]
            matrix[offset + step][offset] = matrix[n][offset + step]
            matrix[n][offset + step] = matrix[n - step][n]
            matrix[n - step][n] = matrix[offset][n - step]
            matrix[offset][n - step] = tmp
            step++
        }
        showMatrix(matrix)
        offset++
    }
}

private fun showMatrix(arr: Array<Array<Int>>) {
    for (ints in arr) {
        for (int in ints) {
            print("$int  ")
            if (int < 10) print(" ")
        }
        println()
    }
    println()
}