package crackin.SearchAndSort

fun main() {

}

fun sortAnagrams(list: List<String>): List<String> {
   return list.sortedWith(AnagramComparator())
}

class AnagramComparator : Comparator<String> {

    override fun compare(o1: String, o2: String): Int {
        val first = o1.toCharArray()
        first.sort()
        val second = o2.toCharArray()
        second.sort()
        return first.joinToString("").compareTo(
            second
                .joinToString("")
        )
    }

}