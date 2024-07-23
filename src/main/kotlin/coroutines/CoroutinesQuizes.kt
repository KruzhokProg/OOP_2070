package org.example.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.random.Random

suspend fun testSuspend(x: Int): Double {
    val y = Math.pow(x.toDouble(), x.toDouble())
    delay(2000)
    return y
}

val a = object : Continuation<String> {
    override val context: CoroutineContext
        get() = TODO("Not yet implemented")

    override fun resumeWith(result: Result<String>) {
        TODO("Not yet implemented")
    }

}

interface Moveable {
    fun move()
}

class Car : Moveable {
    override fun move() {
        TODO("Not yet implemented")
    }
}

class Aircraft : Moveable {
    override fun move() {
        TODO("Not yet implemented")
    }
}

//fun main(): Unit = runBlocking {
//    testSuspend(4)
////    Car() + Aircraft()
////    val list = listOf(Car(), Aircraft())
//}

//@OptIn(ExperimentalStdlibApi::class)
//fun main() {
////    val transport: Moveable = Car()
////    val transport2: Moveable = Aircraft()
//    var context: CoroutineContext = CoroutineName("some coroutine")
//    val coroutineName: CoroutineName? = context[CoroutineName]
//    context = Job()
//    val coroutineJob: Job? = context[Job]
//    println(context[Job])
//    println(context[CoroutineName])
//    val context2 = Job() + Dispatchers.IO
//    val context3 = SupervisorJob() + CoroutineName("some other coroutine") + Dispatchers.Default
//    println(context2[CoroutineDispatcher])
//    runBlocking {
//        launch(context2) {
//
//        }
//    }
//}

//fun main(): Unit = runBlocking {
//    val name = CoroutineName("Some name")
//    val job = SupervisorJob()
//    launch(name + job) {
//        val childName = coroutineContext[CoroutineName]
//        println(childName === name)
//        launch {
//            launch {
//
//            }
//            launch {
//
//            }
//        }
//        val childJob = coroutineContext[Job]
//        println(childJob == job)
//        println(childJob == job.children.first())
//    }
//}

//fun main() {
//    val scope = CoroutineScope(EmptyCoroutineContext)
//    scope.launch {
//        launch {
//            delay(1000)
//            println("1")
//        }
//        launch {
//            delay(500)
//            println("2")
//        }
//    }
//    Thread.sleep(2000)
//}

//suspend fun main() = coroutineScope {
//    val myJob = SupervisorJob()
//    val job = launch(myJob) {
//        launch(myJob) {
//            delay(1000)
//            throw RuntimeException()
//        }
//        launch(myJob) {
//            delay(2000)
//            println("second coroutine finished")
//        }
//    }
//    Thread.sleep(3000)
//}

//fun main() {
//    val myScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
//    myScope.launch {
//        myScope.launch {
//            delay(1000)
//            throw RuntimeException()
//        }
//        myScope.launch {
//            delay(2000)
//            println("second coroutine finished")
//        }
//    }
//    Thread.sleep(3000)
//}

//suspend fun main(): Unit = supervisorScope {
//    launch {
//        delay(1000)
//        throw RuntimeException()
//    }
//    launch {
//        delay(2000)
//        println("second coroutine finished")
//    }
//}

//suspend fun main(): Unit = coroutineScope {
//    try {
//        launch {
//            delay(1000)
//            throw RuntimeException()
//        }
//    } catch (e: Exception) {
//        println(e.message.toString())
//    }
//}

//suspend fun main(): Unit = coroutineScope {
//    launch {
//        try {
//            delay(1000)
//            throw RuntimeException("Error")
//        } catch (e: Exception) {
//            println(e.message.toString())
//        }
//    }
//}

fun main(): Unit = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("caught by handler: $exception")
    }

//    val scope = CoroutineScope(SupervisorJob() + handler)
    try {
        withTimeout(500) {
            val resList = (0..1000).map {
                async {
                    delay(1000 * Random.nextLong(1, 4))
//                    if (it % 2 == 1) throw CancellationException("hgdfdsf")
                    it*it
                }
            }
            resList.awaitAll().forEachIndexed { index, i ->
                println("$index $i")
            }
        }

    } catch (e: CancellationException) {
        println("Error ${e.message}")
    }
//
//    scope.launch {
//        delay(100)
//        throw RuntimeException("Exception!")
//    }
//    scope.launch {
//        delay(200)
//        println("Result")
//    }
//    delay(300)
}