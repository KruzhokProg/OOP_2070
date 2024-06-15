package org.example.lambda

interface MyFunction {
    fun invoke()
}

val INSTANCE: MyFunction = object : MyFunction {
    override fun invoke() {
        println("Hello")
    }
}

fun bar(block: MyFunction) {
    println("before block")
    block.invoke()
    println("after block")
}

fun main() {
    bar(INSTANCE)
}