package tasks

fun main() {
    val list = mutableListOf<List<String>>()
    generate(listOf("aaa", "bbb", "ccc", "ddd", "eee"), 0, list)
    for (tmp in list) {
        for (s in tmp) {
            print("$s, ")
        }
        println()
    }
}

private fun generate(list: List<String>, position: Int, subList: MutableList<List<String>>){
    if (position == list.size){
        subList.add(listOf(" "))
    } else {
        generate(list, position + 1, subList)
        val mTmp = mutableListOf<List<String>>()
        for (seq in subList) {
            val tmp = mutableListOf<String>()
            val rev = mutableListOf<String>()
            tmp.addAll(seq)
            rev.add(list[position])
            rev.addAll(seq)
            tmp.add(list[position])
            mTmp.add(tmp)
            mTmp.add(rev)
        }
        subList.addAll(mTmp)
    }
}