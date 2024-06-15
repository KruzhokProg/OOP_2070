package org.example.lambda

inline fun <reified T> create(a: Any): T {
    return T::class.java.newInstance()
    TODO()
}