fun main() {
    val arr = arrayOf(
        arrayOf(1, 1, 1, 1, 1, 1, 1),
        arrayOf(1, 1, 0, 1, 0, 0, 1),
        arrayOf(1, 1, 0, 0, 0, 0, 0),
        arrayOf(1, 0, 0, 0, 0, 0, 0),
        arrayOf(1, 0, 0, 0, 0, 0, 0),
        arrayOf(1, 1, 0, 1, 0, 0, 0),
        arrayOf(1, 1, 1, 1, 0, 0, 0)
    )
    show(arr)
    fillColor(arr, 2, 3, 2)
    show(arr)
}

fun fillColor(array: Array<Array<Int>>, color: Int, x: Int, y: Int) {
    if (array[x][y] == color) return
    colored(array, color, x, y, array[x][y])
}

fun colored(array: Array<Array<Int>>, color: Int, x: Int, y: Int, selectedColor: Int) {
    if (x < 0 || x > array.size - 1 || y < 0 || y > array[0].size - 1) return

    if (array[x][y] == selectedColor){
        array[x][y] = color
        colored(array, color, x + 1, y, selectedColor)
        colored(array, color, x - 1, y, selectedColor)
        colored(array, color, x, y + 1, selectedColor)
        colored(array, color, x, y - 1, selectedColor)
    }
}

fun show(array: Array<Array<Int>>) {
    for (colors in array) {
        for (color in colors) {
            print("$color, ")
        }
        println()
    }
    println()
    println()
}