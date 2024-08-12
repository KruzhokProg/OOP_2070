package org.example.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
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

//RENDEVOUZ(default)
//suspend fun main(): Unit = coroutineScope {
//    val channel = produce(capacity = Channel.RENDEZVOUS) {
//        // or produce(capacity = Channel.RENDEZVOUS) {
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

//CONFLATED(только последний, остальные будут перезаписаны)
//suspend fun main(): Unit = coroutineScope {
//    val channel = produce(capacity = Channel.CONFLATED) {
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

//Fan-out (корутина отправляет по одному каналу, много корутин принимают) (consumeEach небезопасен в этом случае, нужен обычный цикл)
// у канала есть очередь ожидания элементов корутинами
//@OptIn(ExperimentalCoroutinesApi::class)
//fun CoroutineScope.produceNumbers() = produce {
//    repeat(10) {
//        delay(35)
//        send(it)
//        Channel
//    }
//}
//
//fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
//    for (msg in channel) {
//        println("#$id received $msg")
//    }
//}
//// fair distribution
//suspend fun main(): Unit = coroutineScope {
//    val channel = produceNumbers()
//    repeat(3) { id ->
//        delay(10)
//        launchProcessor(id, channel)
//    }
//}

//Fan-in (множество корутин отправляют данные корутине по одному каналу)
//suspend fun sendString(
//    channel: SendChannel<String>,
//    text: String,
//    time: Long
//) {
//    while (true) {
//        delay(time)
//        channel.send(text)
//    }
//}
//
//fun main() = runBlocking {
//    val channel = Channel<String>()
//    launch { sendString(channel, "foo", 200L) }
//    launch { sendString(channel, "BAR!", 500L) }
//    repeat(10) {
//        println(channel.receive())
//    }
//}

//PIPELINES
//fun CoroutineScope.numbers(): ReceiveChannel<Int> = produce {
//    repeat(3) { num ->
//        send(num + 1)
//    }
//}
//
//fun CoroutineScope.square(numbers: ReceiveChannel<Int>) = produce {
//    for (num in numbers) {
//        send(num * num)
//    }
//}
//
//suspend fun main() = coroutineScope {
//    val numbers = numbers()
//    val squared = square(numbers)
//    for (num in squared) {
//        println(num)
//    }
//}


//Coroutines vs Sequence
fun m(i: Int): Int {
    print("m$i ")
    return i * i
}

fun f(i: Int): Boolean {
    print("f$i ")
    return i >= 10
}

//fun main() {
//    listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//        .map { m(it) }
//        .find { f(it) }
//        .let { print(it) }
//    // m1 m2 m3 m4 m5 m6 m7 m8 m9 m10 f1 f4 f9 f16 16
//    println()
//    sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//        .map { m(it) }
//        .find { f(it) }
//        .let { print(it) }
//    // m1 f1 m2 f4 m3 f9 m4 f16 16
//}

//Hot, Cold Channels
// HOT CHANNELS
//private fun CoroutineScope.makeChannel() = produce {
//    println("Channel started")
//    for (i in 1..3) {
//        delay(1000)
//        send(i)
//    }
//}
//
//suspend fun main() = coroutineScope {
//    val channel = makeChannel()
//    delay(1000)
//    println("Calling channel...")
//    for (value in channel) { println(value) }
//    println("Consuming again...")
//    for (value in channel) { println(value) }
//}

fun getList(): List<String> = List(3) {
    Thread.sleep(1000)
    "User$it"
}
//
fun getSequence(): Sequence<String> = sequence {
    repeat(3) {
        Thread.sleep(1000)
        yield("User$it") // кроме yield больше ничего не можем использовать из suspend
    }
}
//
//fun main() {
//    val time = measureTimeMillis {
////        val list = getList()
//        val list = getSequence() // but sequence not suspending
//        println("Function started")
//        list.forEach { println(it) }
//        list.forEach { println(it) }
//    }
//    println(time)
//}

//@OptIn(ObsoleteCoroutinesApi::class)
//suspend fun main() {
//    withContext(newSingleThreadContext("main")) {
//        launch {
//            repeat(3) {
//                delay(100)
//                println("Processing on coroutine")
//            }
//        }
//
//        val list = getSequence()
//        list.forEach { println(it) }
//    }
//}

//Flow
private fun makeFlow() = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

// @OptIn(InternalCoroutinesApi::class)
// suspend fun main() {
//    withContext(newSingleThreadContext("main")) {
//        launch {
//            repeat(3) {
//                delay(100)
//                println("Processing on coroutine")
//            }
//        }
//
//        val list = makeFlow()
//        list.collect {
//            println(it)
//        }
//    }
//}

//@OptIn(InternalCoroutinesApi::class)
//suspend fun main() = coroutineScope {
//    val flow = makeFlow()
//    delay(1000)
//    println("Calling flow...")
//    flow.collect { println(it) }
//    println("Consuming again...")
//    flow.collect { println(it) }
//}

//---------------------------------
fun usersFlow(): Flow<String> = flow {
    repeat(3) {
        delay(1000)
        val ctx = currentCoroutineContext()
        val name = ctx[CoroutineName]?.name
        emit("User$it in $name")
    }
}

//suspend fun main() {
//    val users = usersFlow() // это не саспенд функция, не требует скоупа
//
//    withContext(CoroutineName("Name")) {
//        val job = launch {
//            users.collect { println(it) }
//        }
//
//        launch {
//            delay(2100)
//            println("I got enough")
//            job.cancel() // say other coroutine to stop
//        }
//    }
//}

//ДЗ
data class UserData(val name: String) // строка на странице
interface UserApi {
    suspend fun takePage(pageNumber: Int): List<UserData>
}
class FakeUserApi : UserApi {
    // генерация списка UserData (100 UserData)
    // pagerSize = 10 количество UserData на одной странице (аналог страниц книги)
    override suspend fun takePage(pageNumber: Int): List<UserData> {
        TODO("Not yet implemented")
//        delay
//        возврат постраничных данных
    }

}

fun allUsersFlow(api: UserApi): Flow<UserData> = flow {
//    начинать вывод с номера страницы
//    получать список UserData последовательно со всех страниц(1, 2, 3 ...)
//    пока эти данные есть
}
suspend fun main() {
    val api = FakeUserApi()
    val users = allUsersFlow(api)
    users.collect { println(it) }
}