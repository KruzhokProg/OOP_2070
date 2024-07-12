package org.example.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

enum class Product(val description: String, val deliveryTime: Long) {
    DOORS("doors", 750),
    WINDOWS("windows", 1_250)
}

//fun order(item: Product): Product {
//    println("ORDER EN ROUTE  >>> The ${item.description} are on the way!")
//    Thread.sleep(item.deliveryTime)
//    println("ORDER DELIVERED >>> Your ${item.description} have arrived.")
//    return item
//}

//fun perform(taskName: String) {
//    println("STARTING TASK   >>> $taskName")
//    Thread.sleep(1_000)
//    println("FINISHED TASK   >>> $taskName")
//}
suspend fun perform(taskName: String) {
    println("STARTING TASK   >>> $taskName")
    repeat(5) {
        Thread.sleep(200)
        yield()
    }
    println("FINISHED TASK   >>> $taskName")
}

suspend fun order(item: Product): Product {
    println("ORDER EN ROUTE  >>> The ${item.description} are on the way!")
    delay(item.deliveryTime)
    println("ORDER DELIVERED >>> Your ${item.description} have arrived.")
    return item
}

//fun main() {
//    val time = measureTimeMillis {
//        runBlocking {
//            val windows = async(Dispatchers.IO) { order(Product.WINDOWS) }
//            val doors = async(Dispatchers.IO) { order(Product.DOORS) }
//            launch(Dispatchers.Default) {
//                perform("laying bricks")
//                launch { perform("installing ${doors.await().description}") }
//                launch { perform("installing ${windows.await().description}") }
//            }
//        }
//    }
//    println(time)
//}

fun main() {
    val time = measureTimeMillis {
        runBlocking {
            val bricksJob = launch(Dispatchers.Default) {
                perform("laying bricks")
            }
            launch(Dispatchers.IO) {
                val windows = order(Product.WINDOWS)
                bricksJob.join()
                withContext(Dispatchers.Default) {
                    perform("install ${windows.description}")
                }
            }
            launch(Dispatchers.IO) {
                val doors = order(Product.DOORS)
                bricksJob.join()
//                cancel()
//                throw Exception("Out of money!")
                withContext(Dispatchers.Default) {
                    perform("install ${doors.description}")
                }
            }
        }
    }
    println(time)
}

//ДЗ
//вычисление n-го числа фибоначчи параллельно
//fibo(100) -> 1 c
//fibo(500) -> 2c
//        max(fibo(n1), fibo(n2))