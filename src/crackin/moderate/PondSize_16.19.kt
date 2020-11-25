package crackin.moderate

import showList

//Pond Sizes:You have an integer matrix representing a plot of land, where the value at that location represents the height above sea level. A value of zero indicates water. A pond is a region of water connected vertically, horizontally, or diagonally. The size of the pond is the total number of connected water cells. Write a method to compute the sizes of all ponds in the matrix.

fun main() {
    val matrix = arrayOf(
        arrayOf(0, 2, 1, 0),
        arrayOf(0, 1, 0, 1),
        arrayOf(1, 1, 0, 1),
        arrayOf(0, 1, 0, 1)
    )
    val res = computePounds(matrix)
    showList(res)
}

fun computePounds(matrix: Array<Array<Int>>): List<Int> {
    val res = mutableListOf<Int>()
    for (r in matrix.indices) {
        for (c in matrix[r].indices) {
            if (matrix[r][c] == 0) {
                res.add(computePounds(matrix, r, c))
            }
        }
    }
    return res
}

fun computePounds(matrix: Array<Array<Int>>, r: Int, c: Int): Int {
    var count = 1
    matrix[r][c] = 1
    if (r - 1 > -1 && matrix[r - 1][c] == 0) {
        count += computePounds(matrix, r - 1, c)
    }

    if (r + 1 < matrix.size && matrix[r + 1][c] == 0) {
        count += computePounds(matrix, r + 1, c)
    }

    if (c - 1 > -1 && matrix[r][c - 1] == 0) {
        count += computePounds(matrix, r, c - 1)
    }

    if (c + 1 < matrix[r].size && matrix[r][c + 1] == 0) {
        count += computePounds(matrix, r, c + 1)
    }

    if (r - 1 > -1 && c - 1 > -1 && matrix[r - 1][c - 1] == 0) {
        count += computePounds(matrix, r - 1, c - 1)
    }

    if (r - 1 > -1 && c + 1 < matrix[r].size && matrix[r - 1][c + 1] == 0) {
        count += computePounds(matrix, r - 1, c + 1)
    }

    if (r + 1 < matrix.size && c - 1 > -1 && matrix[r + 1][c - 1] == 0) {
        count += computePounds(matrix, r + 1, c - 1)
    }

    if (r + 1 < matrix.size && c + 1 < matrix[r].size && matrix[r + 1][c + 1] == 0) {
        count += computePounds(matrix, r + 1, c + 1)
    }

    return count
}









