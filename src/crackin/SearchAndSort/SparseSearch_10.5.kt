package crackin.SearchAndSort

//Sparse Search: Given a sorted array of strings that is interspersed with empty strings, write a method to find the location of a given string.
//EXAMPLE
//Input: ball,{"at", "", "", "", "ball", tt" , u", "car", (tJJ, (OJ, "dad", ""
//Output: 4

fun main() {
//    val list = listOf("at", "", "", "", "ball", "", "", "car", "", "", "dad", "")
    val list = listOf("", "", "", "ball", "", "", "", "", "", "", "")
    println(sparseSearch("ball", list, 0, list.size - 1))
}

fun  sparseSearch(value: String, list: List<String>, from: Int, to: Int): Int {
    if(from > to) return -1
    val mid = (from + to) / 2
    val middle = list[mid]
    if (middle == value) return mid
    if (middle.isNotEmpty() && middle > value) {
        return sparseSearch(value, list, mid + 1, to)
    } else if (middle.isNotEmpty() && middle < value) {
        return sparseSearch(value, list, from, mid - 1)
    }
    var left = mid
    var leftValue = middle
    while (left >= from && leftValue.isEmpty()) {
        leftValue = list[left]
        left--
    }

    if (value == leftValue) return left + 1

    if (value < leftValue) {
        return sparseSearch(value, list, from, left - 1)
    }

    var right = mid
    var rightValue = middle
    while (right <= to && rightValue.isEmpty()) {
        rightValue = list[right]
        right++
    }

    if (value == rightValue) return right + 1
    if (value > rightValue) {
        return sparseSearch(value, list, right + 1, to)
    }
    return -1
}












