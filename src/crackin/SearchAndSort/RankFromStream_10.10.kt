package crackin.SearchAndSort

//Rank from Stream: Imagine you are reading in a stream of integers. Periodically, you wish to be able to look up the rank of a number x (the number of values less than or equal to x). Implement the data structures and algorithms to support these operations. That is, implement the method track(int x), which is called when each number is generated, and the method getRankOfNumber(int x), which returns the number of values less than or equal to x (not including x itself).
//EXAMPLE
//Stream (in order o f appearance) : 5 J getRankOfNumber(l) e getRankOfNumber(3) 1 getRankOfNumber(4) 3


fun main() {
    val rank = RankFromStream<Int>()
    rank.insert(20)
    rank.insert(15)
    rank.insert(25)
    rank.insert(10)
    rank.insert(23)
    rank.insert(24)
    rank.insert(5)
    rank.insert(13)
    println(rank.getRank(13))
}

class RankFromStream<T : Comparable<T>>() {

    private var head: RankNode<T>? = null

    fun insert(value: T) {
        val node = RankNode(value)
        if (head == null) {
            head = node
        } else {
            head!!.insert(node)
        }
    }

    fun getRank(value: T): Int {
        return head?.getRank(value) ?: -1
    }

}

class RankNode<T : Comparable<T>>(
    val value: T,
    var rank: Int = 0,
    var left: RankNode<T>? = null,
    var right: RankNode<T>? = null
) {

    fun getRank(next: T): Int {
        return when {
            next == value -> {
                rank
            }
            next < value -> {
                left?.getRank(next) ?: -1
            }
            else -> {
                right?.getRank(next)?.let {
                    rank + it + 1
                } ?: -1
            }
        }
    }

    fun insert(node: RankNode<T>) {
        if (node.value <= value) {
            rank++
            if (left != null) {
                left!!.insert(node)
            } else {
                left = node
            }
        } else {
            if (right != null) {
                right!!.insert(node)
            } else {
                right = node
            }
        }
    }
}