package y.m

import java.util.concurrent.BlockingDeque
import java.util.concurrent.LinkedBlockingDeque

fun main() {
    val lock = Any()
    val deque = LinkedBlockingDeque<Int>()
    for (i in 0..40) {
        deque.add(i)
    }
    for (i in 0..40) {
        Thread(FootMany(i, deque, lock)).start()
    }
}

class FootMany(
    private val name: Int,
    private val deque: BlockingDeque<Int>,
    private val lock: Any
) : Runnable {

    override fun run() {
        while (true) {
            synchronized(lock) {
                val task = deque.first
                if (task == name) {
                    deque.pollFirst()
                    step()
                    deque.putLast(task)
                }
            }
        }
    }

    private fun step() {
        println("Step by $name")
    }
}