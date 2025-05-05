package db

import java.util.concurrent.ConcurrentHashMap
import kotlin.concurrent.thread
import kotlin.random.Random
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

fun main() {
    val db = Db()
    val ids = listOf("a", "b", "c", "d", "e", "f", "g", "h")
    db.register("a", "a")
    db.register("b", "a")
    db.register("c", "a")
    db.register("d", "a")

    db.register("e", "b")
    db.register("f", "b")
    db.register("g", "b")
    db.register("h", "b")

    val t = thread {
        for (i in 0..Long.MAX_VALUE) {
            thread {
                val data = (1..100).random()
                val id = ids[ids.indices.random()]
                db.update(id, i, data)
            }.join()
        }
    }

    val t2 = thread {
        while (t.isAlive) {
            thread {
                val timeFrom = (0..20000L).random()
                val timeTo = (timeFrom + 100..timeFrom + 1000).random()
                val res = db.getAvg(timeFrom, timeTo, getDeviceType())
                println(res)
            }.join()
        }
    }
    t.join()
    t2.join()
}

private fun getDeviceType(): String {
    val type = if (Random.nextBoolean()) "a" else "b"
    return type
}

class Db {

    private val eventsByDeviceType = ConcurrentHashMap<String, ThreadSafeNodeList>()
    private val eventsByDeviceId = ConcurrentHashMap<String, ThreadSafeNodeList>()

    fun register(deviceId: String, deviceType: String) {
        eventsByDeviceType.computeIfAbsent(deviceType) { ThreadSafeNodeList() }.also { list ->
            eventsByDeviceId[deviceId] = list
        }
    }

    fun update(deviceId: String, timeStamp: Long, data: Int) {
        val list = eventsByDeviceId[deviceId]
        list?.add(timeStamp, data)
    }

    fun getAvg(timeFrom: Long, timeTo: Long, deviceType: String): Double {
        val list = eventsByDeviceType[deviceType] ?: return 0.0
        return list.getAvgValue(timeFrom, timeTo)
    }

}

class ThreadSafeNodeList {

    private val list = mutableListOf<Node>()
    private val lock = ReentrantReadWriteLock()

    fun add(timeStamp: Long, data: Int) {
        lock.write {
            val node = if (list.isEmpty()) {
                Node(timeStamp, data.toLong())
            } else {
                Node(timeStamp, data.toLong() + list.last().data)
            }
            list.add(node)
        }
    }

    fun getAvgValue(timeFrom: Long, timeTo: Long): Double {
        return lock.read {

            val searchResult = list.binarySearch { compareValues(it.timeStamp, timeFrom) }
            val s = if (searchResult >= 0) searchResult
            else -(searchResult + 1)
            if (s == list.size || list[s].timeStamp > timeTo) return 0.0

            val searchResultEnd = list.binarySearch { compareValues(it.timeStamp, timeTo) }
            val e = if (searchResultEnd >= 0) searchResultEnd
            else -(searchResultEnd + 1) - 1
            if (e < 0) return 0.0
            if (s > e) return 0.0
            val startData = if (s > 0) list[s - 1].data else 0L
            val endData = list[e].data
            (endData - startData).toDouble() / (e - s + 1).toDouble()
        }
    }

}

data class Node(
    val timeStamp: Long,
    val data: Long,
)