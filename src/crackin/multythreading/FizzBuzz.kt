package crackin.multythreading

import java.util.concurrent.locks.ReentrantLock

fun main() {
    val list = listOf(
        FizzBuzz(30, true, true, "FizzBuzz"),
        FizzBuzz(30, true, false, "Fizz"),
        FizzBuzz(30, false, true, "Buzz"),
        Incrementer(30)
    )

    for (thread in list) {
        thread.start()
    }
}

class Incrementer(private val n: Int) : FizzBuzz(n, false, false, "") {
    override fun run() {
        while (count < n) {
            synchronized(lock) {
                count++
            }
        }
    }
}

open class FizzBuzz(
    private val n: Int,
    private val div3: Boolean,
    private val div5: Boolean,
    private val text: String
) : Thread() {

    override fun run() {
        while (count < n) {
            synchronized(lock) {
                if ((count % 3 == 0) == div3 && (count % 5 == 0) == div5) {
                    println(text)
                    count++
                }
            }
        }
    }

    companion object {
        var count = 0
        val lock = ReentrantLock()
    }
}