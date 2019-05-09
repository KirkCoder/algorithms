import sort.showArray

fun main() {
    val arr = arrayOf(
        78, 78, 79, 89, 96, 96, 98, 98, 0,
        0,
        1,
        6,
        8,
        8,
        11,
        12,
        12,
        23,
        24,
        25,
        28,
        37,
        40,
        42,
        47,
        51,
        64,
        65,
        66,
        69,
        73,
        76
    )
    val arr2 = arrayOf(10, 10, 10, 7, 10, 10, 10, 10, 10, 10, 10)
    showArray(arr2)
    println(searchInShift(arr2, 0, arr2.size - 1, 7))
}

fun searchInShift(arr: Array<Int>, start: Int, end: Int, element: Int): Int {
    val mid = (start + end) / 2
    if (start > end) return  -1
    if (arr[mid] == element) return mid

    return if (arr[start] < arr[mid]) {
        if (element < arr[mid] && element >= arr[start]) {
            searchInShift(arr, start, mid - 1, element)
        } else {
            searchInShift(arr, mid + 1, end, element)
        }
    } else if (arr[mid] > arr[end]) {
        if (element > arr[mid] && element <= arr[end]) {
            searchInShift(arr, mid + 1, end, element)
        } else {
            searchInShift(arr, start, mid - 1, element)
        }
    } else {
        val tmp = searchInShift(arr, start, mid - 1, element)
        if (tmp == -1) {
            searchInShift(arr, mid + 1, end, element)
        } else {
            tmp
        }
    }
}