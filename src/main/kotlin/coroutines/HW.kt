package org.example.coroutines

import kotlinx.coroutines.*
import java.math.BigInteger
import kotlin.system.measureTimeMillis

//import kotlin.system.measureTimeMillis


suspend fun fibonacci(count: Int) {
    var lastNumbers = Pair(1, 1)
    repeat(count) {
        println("${Thread.currentThread().name}, $lastNumbers")
        lastNumbers = Pair(lastNumbers.second, lastNumbers.run { first + second })
//        yield()
    }
}
//suspend fun main() {
////    1
////    println(measureTimeMillis {
////        runBlocking {
////            launch(Dispatchers.Default) { fibonacci(100) }
////            launch(Dispatchers.Default) { fibonacci(500) }
////        }
////    })
//
////    2
////    println(measureTimeMillis {
////        runBlocking {
////            launch { fibonacci(100) }
////            launch { fibonacci(500) }
////        }
////    })
//
//    // winner version
//    val time = measureTimeMillis {
//        coroutineScope {
////            val res1 = async { fibonacci(20) }
////            val res2 = async { fibonacci(40) }
////            println("${res1.await()}, ${res2.await()}")
//            launch { fibonacci(20) }
//            launch { fibonacci(40) }
//        }
//    }
//    println(time)
//}