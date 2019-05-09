fun main() {
    val arr = arrayOf(
        arrayOf(15, 20, 70, 85),
        arrayOf(20, 35, 80, 95),
        arrayOf(30, 55, 95, 105),
        arrayOf(60, 80, 100, 120)
    )
    findElement(arr, 55)
}

fun findElement(arr: Array<Array<Int>>, element: Int) {
    var x = arr[0].size - 1
    var y = 0

    while (y < arr.size && x > -1){
        if (arr[y][x] == element) {
            println("$y, $x")
            return
        }
        else if(arr[y][x] < element){
            y++
        } else {
            x--
        }
    }
}