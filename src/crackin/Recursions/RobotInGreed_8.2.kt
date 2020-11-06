package crackin.Recursions

//Robot in a Grid: Imagine a robot sitting on the upper left corner of grid with r rows and c columns. The robot can only move in two directions, right and down, but certain cells are "off limits" such that the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to the bottom right.


fun main() {
    findPath()
}

fun findPath() {
    val matrix = arrayOf(
        arrayOf(0, 0, 1, 1, 1, 0),
        arrayOf(1, 0, 2, 2, 2, 0),
        arrayOf(1, 0, 0, 0, 0, 0),
        arrayOf(1, 2, 3, 3, 1, 0),
        arrayOf(1, 0, 0, 0, 0, 0),
        arrayOf(1, 0, 0, 0, 0, 0)
    )
    val path = mutableListOf<Point>()
    val path2 = mutableListOf<Point>()
    findPath(matrix.size - 1, matrix.size - 1, matrix, path)
    findPathWithMemo(matrix.size - 1, matrix.size - 1, matrix, path2, Array(6) { Array(6) { false } })
    for (point in path) {
        println("r:${point.r}, c:${point.c}")
    }
    println()
    for (point in path2) {
        println("r:${point.r}, c:${point.c}")
    }
}

fun findPathWithMemo(
    r: Int,
    c: Int,
    matrix: Array<Array<Int>>,
    path: MutableList<Point>,
    memo: Array<Array<Boolean>>
): Boolean {
    if (r < 0 || c < 0 || matrix[r][c] != 0) {
        return false
    }

    if (memo[r][c]) {
        return false
    }

    memo[r][c] = true

    val targetFound = r == 0 && c == 0

    if (targetFound) {
        path.add(Point(r, c))
        return true
    }

    if (targetFound
        || findPathWithMemo(r, c - 1, matrix, path, memo)
        || findPathWithMemo(r - 1, c, matrix, path, memo)
    ) {
        path.add(Point(r, c))
        return true
    }

    return false
}

fun findPath(r: Int, c: Int, matrix: Array<Array<Int>>, path: MutableList<Point>): Boolean {
    if (r < 0 || c < 0 || matrix[r][c] != 0) {
        return false
    }

    val targetFound = r == 0 && c == 0

    if (targetFound || findPath(r - 1, c, matrix, path) || findPath(r, c - 1, matrix, path)) {
        path.add(Point(r, c))
        return true
    }
    return false
}

data class Point(
    val r: Int,
    val c: Int
)