package org.example.coroutines

import kotlinx.coroutines.*
import org.example.lambda.User
import kotlin.random.Random
import kotlin.system.measureTimeMillis

//fun main(): Unit = runBlocking {
//    launch {// launch(Job()) нарушение иерархии structured concurrency
//        delay(1000)
//        println("Test1")
//    }
//    launch {
//        delay(1000)
//        println("Test2")
//    }
//    val children = coroutineContext[Job]?.children
//    val childrenNum = children?.count()
//    println("Number of children: $childrenNum")
//    children?.forEach { it.join() }
//    println("All tests are done")
//}

//suspend fun main() = coroutineScope {
//    val job = launch {
//        repeat(1_000) { i ->
////            delay(100)
//            yield()
//            Thread.sleep(100)
//            println("Printing $i")
//        }
//    }
//
//    delay(1000)
//    println("Delay")
//    job.cancel()
//    job.join() // Race condition
//    println("Cancelled successfully")
//}

//suspend fun main(): Unit = coroutineScope {
//    val job = Job()
//    launch(job) {
//        try {
//            repeat(1_000) { i ->
//                delay(200)
//                println("Printing $i")
//            }
//        } catch (e: CancellationException) {
//            println(e)
////            throw e
//        }
//    }
//    delay(1100)
//    job.cancelAndJoin()
//    println("Cancelled successfully")
////    delay(1000)
//}

//finally block
//suspend fun main(): Unit = coroutineScope {
//    val job = Job()
//    launch(job) {
//        try {
//            delay(Random.nextLong(2000))
//            println("Done")
//        } finally {
//            println("Will always be printed")
//        }
//    }
//    delay(1000)
//    job.cancelAndJoin()
//}

//just one more call
//suspend fun main(): Unit = coroutineScope {
//    val job = Job()
//    launch(job) {
//        try {
//            delay(2000)
//            println("Job is done")
//        } catch (e: CancellationException) {
//            println(e)
//        }
//        finally {
//            println("Finally")
//            launch(Job()) { // will be ignored
//                println("Will not be printed")
//            }
//            delay(1000) // here exception is thrown
//            println("Will not be printed")
//        }
//    }
//    delay(1000)
//    job.cancelAndJoin()
//    println("Cancel done")
//}


//suspend fun main(): Unit = coroutineScope {
//    val job = Job()
//    launch(job) {
//        try {
//            delay(200)
//            println("Coroutine finished")
//        } finally {
//            println("Finally")
//            withContext(NonCancellable) {
//                delay(1000L)
//                println("Cleanup done")
//            }
//        }
//    }
//    delay(100)
//    job.cancelAndJoin()
//    println("Done")
//}

//Cancellation happens on suspension points
//suspend fun main(): Unit = coroutineScope {
//    val job = Job()
//    launch(job) {
//        repeat(1_000) { i ->
//            yield()
//            Thread.sleep(200) // We might have some complex operations or reading files here
//            println("Printing $i")
//        }
//    }
//    delay(1000)
//    job.cancelAndJoin()
//    println("Cancelled successfully")
//    delay(1000)
//}

//Exception handling (Structured concurrency)
//fun main(): Unit = runBlocking {
//    launch {
//        launch {
//            delay(1000)
//            throw Error("Some error")
//        }
//        launch {
//            delay(2000)
//            println("Will not be printed")
//        }
//        launch {
//            delay(500) // faster than the exception
//            println("Will be printed")
//        }
//    }
//    launch {
//        delay(2000)
//        println("Will not be printed")
//    }
//}

//CancellationException не распространяется к родителям
//object MyNonPropagatingException : CancellationException()

//suspend fun main(): Unit = coroutineScope {
//    launch { // 1
//        launch { // 2
//            delay(2000)
//            println("Will not be printed")
//        }
//        throw MyNonPropagatingException // 3
//    }
//    launch { // 4
//        delay(2000)
//        println("Will be printed")
//    }
//}

//fun main(): Unit = runBlocking {
//    val job = SupervisorJob()
//    val scope = CoroutineScope(job)
//    scope.launch {
//        delay(1000)
//        throw Error("Some error")
//    }
//    scope.launch {
//        delay(2000)
//        println("Will be printed")
//        launch {
//            delay(1000)
//            println("Something")
//        }
//    }
//    delay(2500)
//    job.cancelAndJoin()
//    delay(1000)
//}

//superviserScope - блокирующий по отношению к внешним скоупам
//fun main(): Unit = runBlocking {
//    val time = measureTimeMillis {
//        supervisorScope {
//            launch {
//                delay(1000)
//                throw Error("Some error")
//            }
//            launch {
//                delay(2000)
//                println("Will be printed")
//                launch {
//                    delay(1000)
//                    println("Something")
//                }
//            }
//        }
//        delay(1200)
//        println("Done")
//    }
//    println(time)
//}

//suspend fun notify(actions: List<UserAction>) = supervisorScope {
//    actions.forEach { action ->
//        launch {
//            notifyAnalytics(action)
//        }
//    }
//}

//coroutineScope cancellation
//suspend fun longTask() = coroutineScope {
//    launch {
//        delay(1000)
//        val name = coroutineContext[CoroutineName]?.name
//        println("[$name] Finished task 1")
//    }
//    launch {
//        delay(2000)
//        val name = coroutineContext[CoroutineName]?.name
//        println("[$name] Finished task 2")
//    }
//}
//
//fun main(): Unit = runBlocking {
//    val job = launch(CoroutineName("Parent")) {
//        longTask()
//    }
//    delay(1500)
//    job.cancel()
//}

//withTimeout
//suspend fun test(): Int = withTimeout(1500) {
//    delay(1000)
//    println("Still thinking")
//    delay(1000)
//    println("Done!")
//    42
//}
//
//suspend fun main(): Unit = coroutineScope {
//    val time = measureTimeMillis {
//        try {
//            test()
//        } catch (e: TimeoutCancellationException) {
//            println("Cancelled")
//        }
//    }
//    println(time)
//}

//withTimeout generates TimeoutCancellationException
//suspend fun main(): Unit = coroutineScope {
//    launch { // 1
//        launch {
//            delay(2000) // 2
//            println("Will not be printed")
//        }
//        withTimeout(1000) {
//            delay(1500)
//        }
//    }
//    launch { // 3
//        delay(2000)
//        println("Done")
//    }
//}

//withTimeoutOrNull
//suspend fun fetchUser(): User { // Runs forever
//    while (true) {
//        yield()
//    }
//}
//
//suspend fun getUserOrNull(): User? =
//    withTimeoutOrNull(5000) {
//        fetchUser()
//    }
//
//suspend fun main(): Unit = coroutineScope {
//    val user = getUserOrNull()
//    println("User: $user")
//}