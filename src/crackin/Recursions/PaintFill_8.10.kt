package crackin.Recursions

import showMatrix
import java.util.*


//8.10 Paint Fill: Implement the "paint fill" function that one might see on many image editing programs. That is, given a screen (represented by a two-dimensional array of colors), a point, and a new color, fill in the surrounding area until the color changes from the original color.

fun main() {
    val matrix = arrayOf(
        arrayOf(1, 1, 1, 1, 1, 1),
        arrayOf(1, 1, 1, 1, 1, 1),
        arrayOf(1, 1, 1, 1, 1, 1),
        arrayOf(1, 1, 1, 1, 1, 1),
        arrayOf(1, 1, 1, 1, 1, 1),
        arrayOf(1, 1, 1, 1, 1, 1)
    )
    coloredVisual(matrix, 0, 3, 4)
    showMatrix(matrix)
}

fun coloredVisual(matrix: Array<Array<Int>>, color: Int, r: Int, c: Int) {
    if (r < 0 || r >= matrix.size || c < 0 || c >= matrix[0].size) {
        return
    }

    val nexts = LinkedList<MatrixPoint>()
    nexts.add(MatrixPoint(r, c))

    while (nexts.isNotEmpty()) {
        val next = nexts.removeFirst()
        if (matrix[next.r][next.c] != color) {
            matrix[next.r][next.c] = color
            showMatrix(matrix)
            nexts.appendPoint(matrix, next.r + 1, next.c)
            nexts.appendPoint(matrix, next.r - 1, next.c)
            nexts.appendPoint(matrix, next.r, next.c + 1)
            nexts.appendPoint(matrix, next.r, next.c - 1)
        }
    }
}

private fun LinkedList<MatrixPoint>.appendPoint(matrix: Array<Array<Int>>, r: Int, c: Int) {
    if (r > -1 && r < matrix.size && c > -1 && c < matrix[0].size) {
        this.add(MatrixPoint(r, c))
    }
}

data class MatrixPoint(
    val r: Int,
    val c: Int
)

fun colored(matrix: Array<Array<Int>>, color: Int, r: Int, c: Int) {
    if (r < 0 || r >= matrix.size || c < 0 || c >= matrix[0].size) {
        return
    }

    if (matrix[r][c] != color) {
        matrix[r][c] = color
        colored(matrix, color, r + 1, c)
        colored(matrix, color, r - 1, c)
        colored(matrix, color, r, c + 1)
        colored(matrix, color, r, c - 1)
    }
}