package test

import showMatrix


fun main() {
    val matrix = arrayOf(
        arrayOf(0, 1, 1, 1, 1, 1),
        arrayOf(1, 0, 2, 2, 2, 1),
        arrayOf(1, 2, 0, 3, 2, 1),
        arrayOf(1, 2, 3, 3, 2, 1),
        arrayOf(1, 2, 2, 2, 2, 1),
        arrayOf(1, 1, 1, 1, 1, 1)
    )
    rotate(matrix)
    showMatrix(matrix)
}

fun rotate(matrix: Array<Array<Int>>) {
    var offset = 0
    var n = matrix.size - 1
    while (offset < n) {
        var step = 0
        while (offset + step < n) {
            val tmp = matrix[offset + step][offset]
            matrix[offset + step][offset] = matrix[n][offset + step]
            matrix[n][offset + step] = matrix[n - step][n]
            matrix[n - step][n] = matrix[offset][n - step]
            matrix[offset][n - step] = tmp
            step++
        }
        offset++
        n = matrix.size - 1 - offset
    }
}