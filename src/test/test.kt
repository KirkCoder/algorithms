package test

import java.util.concurrent.locks.ReentrantLock

fun main() {
    val tester = Tester()
    val list = mutableListOf<MyThread>()
    for (i in 0 .. 6) {
        list.add(MyThread(i.toString(), tester))
    }

    for (myThread in list) {
        myThread.start()
    }

    println(tester.read())
}

class MyThread(private val id: String, private val tester: Tester) : Thread() {
    override fun run() {
        println("befor from: $id == ${tester.read()}")
        tester.write(id)
        println("after from: $id == ${tester.read()}")
    }
}

class Tester {

    var value: String = "empty"
    private val lock = ReentrantLock()

    fun write(new: String) {
        lock.lock()
        value = new
        lock.unlock()
    }

    fun read(): String {
        lock.lock()
        return try {
            value
        } finally {
            lock.unlock()
        }
    }

}