package tasks

fun main() {
    val list = LinkList<Int>()
    for (i in 0..20) {
        list.put(Node(i))
    }
    list.sout()
    list.reverse()
    list.sout()
}

class Node<T : Comparable<T>>(val data: T) {
    var next: Node<T>? = null
}

class LinkList<T: Comparable<T>>{
    var start: Node<T>? = null

    fun put(node: Node<T>){
        if (start == null){
            start = node
        } else {
            var current = start?.next
            if (current == null){
                start?.next = node
                return
            }
            var tmp = current.next
            while (tmp != null){
                current = tmp
                tmp = current.next
            }
            current?.next = node
        }
    }

    fun sout(){
        var current = start
        while (current != null){
            print("${current.data}, ")
            current = current.next
        }
        println()
    }

    fun reverse(){
        var prev: Node<T>? = null
        var current = start
        var next = start?.next
        while (current != null){
            current.next = prev
            prev = current
            current = next
            next = next?.next
        }
        start = prev
    }
}