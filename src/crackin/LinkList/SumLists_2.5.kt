package crackin.LinkList

import SingleLinkedList

//Sum Lists: You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.
//1
//2
//3 4} 5
//return nullj
//EXAMPLE
//Input: (7-> 1 -> 6) + (5 -> 9 -> 2) .Thatis,617 + 295. Output:2 -> 1 -> 9.Thatis,912.
//FOLLOW UP
//Suppose the digits are stored in forward order. Repeat the above problem. Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).Thatis,617 + 295. Output:9 -> 1 -> 2.Thatis,912.
//
fun main() {
    val first = SingleLinkedList<Int>()
    first.add(7)
    first.add(1)
    first.add(1)
    first.add(1)
    first.add(6)

    val second = SingleLinkedList<Int>()
    second.add(5)
    second.add(9)
    second.add(2)

    sumList(first, second).show()
}

fun reverseList(list: SingleLinkedList<Int>) {
    var prev: SingleLinkedList.Node<Int>? = null
    var next = list.start
    while(next != null) {
        val tmp = next.next
        next.next = prev
        prev = next
        next = tmp
    }
    list.start = prev
}

fun sumList(first: SingleLinkedList<Int>, second: SingleLinkedList<Int>): SingleLinkedList<Int> {
    val result = getNumberFromList(first) + getNumberFromList(second)
    val resultList = SingleLinkedList<Int>()
    var dividerBig = 10
    var dividerSmall = 1
    while (result / dividerSmall > 0) {
        val number = (result % dividerBig) / dividerSmall
        resultList.add(number)
        dividerBig *= 10
        dividerSmall *= 10
    }
    return resultList
}

private fun getNumberFromList(list: SingleLinkedList<Int>): Int {
    val i = list.iterator()
    var increment = 1
    var number = 0
    while (i.hasNext()) {
        val element = i.next()
        number += element * increment
        increment *= 10
    }
    return number
}