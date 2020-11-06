package crackin.Recursions

import showList
import java.util.*

//owers of Hanoi: In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of different sizes which can slide onto any tower.The puzzle starts with disks sorted in ascending order of size from top to bottom (Le., each disk sits on top of an even larger one). You have the following constraints:
//(1) Only one disk can be moved at a time.
//(2) A disk is slid off the top of one tower onto another tower.
//(3) A disk cannot be placed on top of a smaller disk.
//Write a program to move the disks from the first tower to the last using Stacks.

fun main() {
    val from = LinkedList<Int>()
    val middle = LinkedList<Int>()
    val to = LinkedList<Int>()

    for (i in 4 downTo 1) {
        from.add(i)
    }
    move(from.size - 1, from, middle, to)
    showList(from)
    showList(middle)
    showList(to)
}

fun move(n: Int, from: LinkedList<Int>, buffer: LinkedList<Int>, to: LinkedList<Int>) {
    if(n < 0) return
    move(n - 1, from, to, buffer)
    to.add(from.removeLast())
    move(n - 1, buffer, from, to)
}