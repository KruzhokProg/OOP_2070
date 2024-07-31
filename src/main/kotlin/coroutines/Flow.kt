package org.example.coroutines

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

//CHANNEL
//suspend fun main(): Unit = coroutineScope {
//    val channel = Channel<Int>()
//    launch {
//        repeat(5) { index ->
//            delay(1000)
//            println("Producing next one")
//            channel.send(index * 2)
//        }
//    }
//    launch {
//        repeat(5) {
//            val received = channel.receive()
//            println(received)
//        }
//    }
//}

//suspend fun main(): Unit = coroutineScope {
//    val channel = Channel<Int>()
//    launch {
//        repeat(5) { index ->
//            println("Producing next one")
//            delay(1000)
//            channel.send(index * 2)
//        }
//        channel.close() // we can forget about it
//    }
//    launch {
////        for (element in channel) {
////            println(element)
////        }
//        channel.consumeEach { element ->
//            println(element) // if there is an exception, then consumption will wait forever
//        }
//    }
//}

//suspend fun main(): Unit = coroutineScope {
//    val channel = produce {
//        repeat(5) { index ->
//            println("Producing next one")
//            delay(1000)
//            send(index * 2)
//        }
//    }
//    for (element in channel) {
//        println(element)
//    }
//}

//UNLIMITED
//suspend fun main(): Unit = coroutineScope {
//    val time = measureTimeMillis {
//        val channel = produce(capacity = Channel.UNLIMITED) {
//            repeat(5) { index ->
//                send(index * 2)
//                delay(100)
//                println("Sent")
//            }
//        }
//        delay(1000)
//        for (element in channel) {
//            println(element)
//            delay(1000)
//        }
//    }
//    println(time)
//}

//FIXED
//suspend fun main(): Unit = coroutineScope {
//    val channel = produce(capacity = 3) {
//        repeat(5) { index ->
//            send(index * 2)
//            delay(100)
//            println("Sent")
//        }
//    }
//    delay(1000)
//    for (element in channel) {
//        println(element)
//        delay(1000)
//    }
//}

//RENDEVOUZ
suspend fun main(): Unit = coroutineScope {
    val channel = produce(capacity = Channel.RENDEZVOUS) {
        // or produce(capacity = Channel.RENDEZVOUS) {
        repeat(5) { index ->
            send(index * 2)
//            delay(100)
            println("Sent")
        }
    }
    delay(1000)
    for (element in channel) {
        println(element)
//        delay(1000)
    }
}