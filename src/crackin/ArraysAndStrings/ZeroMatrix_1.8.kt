package crackin.ArraysAndStrings

import showMatrix

//Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.

fun main() {
    val matrix = arrayOf(
        arrayOf(1, 1, 1, 1, 1, 0),
        arrayOf(1, 1, 2, 2, 2, 1),
        arrayOf(1, 2, 4, 0, 2, 1),
        arrayOf(1, 2, 3, 3, 2, 1),
        arrayOf(1, 2, 2, 2, 2, 1),
        arrayOf(1, 0, 1, 1, 1, 1)
    )
    zeroMatrix(matrix)
    showMatrix(matrix)
}

private fun zeroMatrix(matrix: Array<Array<Int>>) {
    var rawHasZeros = false
    var columnHasZeros = false

    for (i in matrix.indices) {
        if (matrix[0][i] == 0) {
            rawHasZeros = true
            break
        }
    }

    for (i in matrix.indices) {
        if (matrix[i][0] == 0) {
            columnHasZeros = true
            break
        }
    }

    for (r in matrix.indices) {
        for (c in matrix.indices) {
            if (matrix[r][c] == 0) {
                matrix[r][0] = 0
                matrix[0][c] = 0
            }
        }
    }

    for (i in 1 until matrix.size) {
        if (matrix[0][i] == 0) {
            setColumnToZero(matrix, i)
        }
    }

    for (i in 1 until matrix.size) {
        if (matrix[i][0] == 0) {
            setRawToZero(matrix, i)
        }
    }

    if (rawHasZeros) setRawToZero(matrix, 0)
    if (columnHasZeros) setColumnToZero(matrix, 0)
}

fun setColumnToZero(matrix: Array<Array<Int>>, column: Int) {
    for (i in matrix.indices) {
        matrix[i][column] = 0
    }
}


fun setRawToZero(matrix: Array<Array<Int>>, raw: Int) {
    for (i in matrix.indices) {
        matrix[raw][i] = 0
    }
}