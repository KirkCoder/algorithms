package crackin.SearchAndSort


//10.9 Sorted Matrix Search: Given an M x N matrix in which each row and each column is sorted in ascending order, write a method to find an element.

fun main() {
    val matrix = arrayOf(
        arrayOf(1, 2, 3, 4, 5, 6),
        arrayOf(7, 8, 9, 10, 11, 12),
        arrayOf(13, 14, 15, 16, 17, 18),
        arrayOf(19, 20, 21, 22, 23, 24),
        arrayOf(25, 26, 27, 28, 29, 30),
        arrayOf(31, 32, 33, 34, 35, 36)
    )

    val res = findInMatrix(matrix, 34)
    println(matrix[res!!.first][res.second])
}

fun findInMatrix(matrix: Array<Array<Int>>, value: Int): Pair<Int, Int>? {
    return findInMatrix(matrix, value, 0, 0, matrix.size - 1, matrix[0].size - 1, 0, 0)
}

fun findInMatrix(
    matrix: Array<Array<Int>>,
    value: Int,
    r: Int,
    c: Int,
    maxR: Int,
    maxC: Int,
    minR: Int,
    minC: Int
): Pair<Int, Int>? {
    if (r > maxR || r < minR || c > maxC || c < minC) {
        return null
    }
    val tmp = matrix[r][c]
    if (tmp == value) return Pair(r, c)

    if (tmp > value) {
        val first = findInMatrix(matrix, value, minR, c, r - 1, maxC, minR, c)
        if (first != null) return first
        return findInMatrix(matrix, value, r, minC, maxR, c - 1, r, minC)
    } else {
        if (r == maxR && c == maxC) {
            return null
        }
        val newR = if (r < maxR) r + 1 else r
        val newC = if (c < maxC) c + 1 else c
        return findInMatrix(matrix, value, newR, newC, maxR, maxC, minR, minC)
    }
}