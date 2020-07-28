package crackin.ArraysAndStrings

import showMatrix

fun main() {
    val matrix = arrayOf(
        arrayOf(1,  2,  3,  4),
        arrayOf(12, 13, 14,  5),
        arrayOf(11, 16, 15,  6),
        arrayOf(10,  9,  8,  7)
    )

//    val matrix = arrayOf(
//        arrayOf(0, 1, 1, 1, 1, 1),
//        arrayOf(1, 0, 2, 2, 2, 1),
//        arrayOf(1, 2, 0, 3, 2, 1),
//        arrayOf(1, 2, 3, 3, 2, 1),
//        arrayOf(1, 2, 2, 2, 2, 1),
//        arrayOf(1, 1, 1, 1, 1, 1)
//    )

//    val matrix = arrayOf(
//        arrayOf(1, 2),
//        arrayOf(3, 4)
//    )
    rotateMatrix(matrix)
    showMatrix(matrix)
}

fun rotateMatrix(matrix: Array<Array<Int>>) {
    var start = 0
    var n = matrix.size - 1
    while(start < n) {
        var offset = 0
        while (start + offset < n) {
            val tmp = matrix[start + offset][start]
            matrix[start + offset][start] = matrix[n][start + offset]
            matrix[n][start + offset] = matrix[n - offset][n]
            matrix[n - offset][n] = matrix[start][n - offset]
            matrix[start][n - offset] = tmp
            offset++
        }
        showMatrix(matrix)
        start += 1
        n -= 1
    }
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