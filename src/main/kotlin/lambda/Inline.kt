package org.example.lambda

inline fun bar(block: () -> Unit) {
    println("before block")
    block.invoke()
    println("after block")
}

fun main() {
    bar {
        println("Hello")
    }
}