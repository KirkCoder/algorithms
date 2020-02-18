fun main() {
    generateAnagram("abc").forEach {
        println(it)
    }
    generateAnagramArr(arrayOf('a', 'b', 'c'), 0)
}

fun generateAnagram(str: String): List<String> {
    val list = mutableListOf<String>()
    if (str.isEmpty()) {
        list.add("")
        return list
    }

    val first = str[0]
    val nextStr = str.substring(1)
    val anagrams = generateAnagram(nextStr)
    for (s in anagrams) {
        for (i in 0 .. s.length){
            list.add(getAnagram(first, s, i))
        }
    }
    return list
}

fun getAnagram(char: Char, s: String, i: Int): String {
    return s.substring(0, i) + char + s.substring(i)
}

fun generateAnagramArr(arr: Array<Char>, position: Int) {
    if (position >= arr.size) return
    for (i in position until arr.size) {
        generateAnagramArr(arr, position + 1)
        if (position == arr.size - 2) showArray(arr)
        shift(arr, position)
    }
}

fun shift(arr: Array<Char>, position: Int) {
    val element = arr[position]
    for (i in position + 1 until arr.size) {
        arr[i - 1] = arr[i]
    }
    arr[arr.size - 1] = element
}
