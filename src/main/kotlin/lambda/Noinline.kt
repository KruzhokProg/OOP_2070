package org.example.lambda

inline fun bar(
    block1: () -> Unit,
    noinline block2: () -> Unit
) {
    println("before block 1")
    block1.invoke()
    println("between blocks")
    block2.invoke()

    test(block2)
}

fun test(block: () -> Unit) {

}

fun main() {
    bar(
        block1 = { println("111") },
        block2 = { println("222") }
    )
}
