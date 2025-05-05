package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.random.Random
import kotlin.system.measureTimeMillis


fun main() = runBlocking<Unit> {
    try {
        val d = GlobalScope.launch(SupervisorJob()) {
            log("l1")
            throw ArithmeticException()
        }
//        d.join()
//        val d2 = GlobalScope.async {
//            log("l1")
//            throw ArithmeticException()
//        }
//        log("pre await")
//        d2.await()
    } catch (e: Throwable) {
        log("cathed")
    }

    log("l2")
    delay(1000L)
    log("finish")
}

fun log(message: Any?) {
    println("[${Thread.currentThread().name}] $message")
}


//fun main() {
//    val channel = Channel<Int>(UNLIMITED)
//}