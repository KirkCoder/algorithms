package tasks

fun main() {
    val size = 8
    val list = mutableListOf<Array<Int?>>()
    calculate(size, 0, arrayOfNulls(size), list)
    for (arrayOfInts in list) {
        for (arrayOfInt in arrayOfInts) {
            print(arrayOfInt)
        }
        println()
    }
}

fun calculate(size: Int, row: Int, columns: Array<Int?>, result: MutableList<Array<Int?>>) {
    if (row == size) {
        result.add(columns.clone())
    } else {
        for (column in 0 until size) {
            if (isValid(columns, row, column)) {
                columns[row] = column
                calculate(size, row + 1, columns, result)
            }
        }
    }
}

fun isValid(columns: Array<Int?>, row: Int, column: Int): Boolean {
    for (r in 0 until row) {
        val nextColumn = columns[r]
        if (column == nextColumn) return false

        if (nextColumn != null && Math.abs(column - nextColumn) == row - r) return false
    }
    return true
}