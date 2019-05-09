fun main() {
    val arr = arrayOf(
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0)
    )
    val list = mutableListOf<Int>()
//    for (x in 0 until arr.size) {
//        for (y in 0 until arr.size) {
//            findStep(x, y, arr.copy(), list)
//        }
//    }
    findStep(0, 0, arr, list)
    var res = 0
    for (i in list) {
        res += i
    }
    println(res)
}

fun findStep(x: Int, y: Int, arr: Array<Array<Int>>, res: MutableList<Int>) {
    if (isFull(arr)) {
        res.add(1)
        return
    } else {
        if (x > -1 && x < arr.size && y > -1 && y < arr.size && arr[x][y] != 1) {
            arr[x][y] = 1
            findStep(x + 2, y + 1, arr.copy(), res)
            findStep(x - 2, y + 1, arr.copy(), res)
            findStep(x + 2, y - 1, arr.copy(), res)
            findStep(x - 2, y - 1, arr.copy(), res)
            findStep(x + 1, y + 2, arr.copy(), res)
            findStep(x + 1, y - 2, arr.copy(), res)
            findStep(x - 1, y - 2, arr.copy(), res)
            findStep(x - 1, y + 2, arr.copy(), res)
        } else {
            return
        }
    }
}

fun Array<Array<Int>>.copy(): Array<Array<Int>> {
    var i = 0
    return Array(size) {
        this[i++].clone()
    }
}

fun isFull(arr: Array<Array<Int>>): Boolean {
    for (ints in arr) {
        for (int in ints) {
            if (int == 0) return false
        }
    }
    return true
}