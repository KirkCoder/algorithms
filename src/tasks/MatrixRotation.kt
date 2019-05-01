package tasks

fun main() {
    val matrix = arrayOf(
        arrayOf(1,  2,  3,  4),
        arrayOf(12, 13, 14, 5),
        arrayOf(11, 16, 15, 6),
        arrayOf(10, 9,  8,  7)
    )
    sout(matrix)
    rotate(matrix)
    sout(matrix)
}

fun rotate(matrix: Array<Array<Int>>) {
    for (start in 0 until matrix.size / 2){
        val end = matrix.size - 1 - start
        for(i in start until end){
            val offset = i - start
            val tmp = matrix[start][i]
            matrix[start][i] = matrix[end - offset][start]
            matrix[end - offset][start] = matrix[end][end - offset]
            matrix[end][end - offset] = matrix[i][end]
            matrix[i][end] = tmp
        }
        sout(matrix)
    }
}

fun sout(arr: Array<Array<Int>>){
    for (ints in arr) {
        for (int in ints) {
            print("$int  ")
        }
        println()
    }
    println()
}