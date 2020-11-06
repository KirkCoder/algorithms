package crackin.Recursions

import showMatrix

//Eight Queens: Write an algorithm to print all ways of arranging eight queens on an 8x8 chess board so that none of them share the same row, column, or diagonal. In this case, "diagonal" means all diagonals, not just the two that bisect the board.

fun main() {
    val matrix = arrayOf(
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0)
    )
    println(positionQuin(matrix, 7, IntArray(8)))
    showMatrix(matrix)
}

fun positionQuin(matrix: Array<Array<Int>>, row: Int, usedColumns: IntArray): Boolean {
    if (row < 0) return true
    for (c in matrix.indices) {
        if (usedColumns[c] == 1) continue
        if (checkPosition(matrix, row, c)) {
            matrix[row][c] = 1
            usedColumns[c] = 1
            if (positionQuin(matrix, row - 1, usedColumns)) {
                return true
            }
            matrix[row][c] = 0
            usedColumns[c] = 0
        }
    }
    return false
}

private fun checkPosition(matrix: Array<Array<Int>>, i: Int, j: Int): Boolean {
    var r = i
    var c = j

    while (r > -1) {
        if (matrix[r][c] != 0) {
            return false
        }
        r--
    }
    r = i

    while (r < matrix.size) {
        if (matrix[r][c] != 0) {
            return false
        }
        r++
    }
    r = i

    while (r > -1 && c > -1) {
        if (matrix[r][c] != 0) {
            return false
        }
        r--
        c--
    }
    c = j
    r = i

    while (r < matrix.size && c < matrix[0].size) {
        if (matrix[r][c] != 0) {
            return false
        }
        r++
        c++
    }

    c = j
    r = i

    while (r > -1 && c < matrix[0].size) {
        if (matrix[r][c] != 0) {
            return false
        }
        r--
        c++
    }

    c = j
    r = i

    while (c > -1 && r < matrix.size) {
        if (matrix[r][c] != 0) {
            return false
        }
        c--
        r++
    }

    return true
}






