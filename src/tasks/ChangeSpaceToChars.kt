package tasks

fun main() {
    val s = "Mr John Smith    "
    doit(s)
}

fun doit(s: String){
    val arr = s.toCharArray()
    var space = 0
    for (c in arr) {
        if (c == ' ') space++
    }

    var newLength = arr.size + space * 2
    val newArr = arrayOfNulls<Char>(newLength)

    var length = arr.size - 1
    newLength--
    while (newLength > -1){
        val c = arr[length--]
        if (c == ' '){
            newArr[newLength--] = '0'
            newArr[newLength--] = '2'
            newArr[newLength--] = '%'
        } else {
            newArr[newLength--] = c
        }
    }

    for (c in newArr) {
        print("$c")
    }
}