package crackin.multythreading

import java.util.concurrent.locks.ReentrantLock

fun main() {
    val philosophers = mutableListOf<Philosopher>()
    val chopsticks = mutableListOf<Chopstick>()
    for (i in 0..7) {
        chopsticks.add(Chopstick(i))
    }
    for (i in 0..7) {
        val philosopher = when (i) {
            0 -> {
                Philosopher(i, chopsticks[7], chopsticks[0])
            }
            else -> {
                Philosopher(i, chopsticks[i - 1], chopsticks[i])
            }
        }
        philosophers.add(philosopher)
    }
    for (philosopher in philosophers) {
        philosopher.start()
    }
}

class Philosopher(
    val id: Int,
    val left: Chopstick,
    val right: Chopstick
) : Thread() {
    var eated: Boolean = false
        private set

    override fun run() {
        while (!eated) {
            if (left.get()) {
                if (right.get()) {
                    eated = true
                    right.release()
                }
                left.release()
            }
        }
        println("$id - finished")
    }
}

class Chopstick(
    val id: Int
) {
    val lock = ReentrantLock()

    fun get(): Boolean {
        return lock.tryLock()
    }

    fun release() {
        lock.unlock()
    }
}