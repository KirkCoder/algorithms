import sort.generateArray
import sort.showArray

fun main() {
    val arr = generateArray().toSet().toTypedArray()
    showArray(arr)
    genPeakAndThrough(arr)
    showArray(arr)
}

fun genPeakAndThrough(arr: Array<Int>) {
    for (i in 1 until arr.size - 1 step 2) {
        val max = Math.max(arr[i - 1], Math.max(arr[i], arr[i + 1]))
        if (max != arr[i]) {
            if (max == arr[i - 1]) {
                val tmp = arr[i - 1]
                arr[i - 1] = arr[i]
                arr[i] = tmp
            } else {
                val tmp = arr[i + 1]
                arr[i + 1] = arr[i]
                arr[i] = tmp
            }
        }
    }
}