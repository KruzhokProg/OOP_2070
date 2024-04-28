package org.example

interface QuadrantDifference {
    // a**2 - b**2 = (a-b)*(a+b)
    fun value(): Long
}

class QuadrantDifferenceImpl(private val numbers: Numbers): QuadrantDifference {
    override fun value(): Long {
        TODO("Not yet implemented")
        // c try catch и (без!!!)
    }
}

fun main() {
    QuadrantDifferenceImpl(NumbersImpl(Int.MAX_VALUE , 1)).value()
}

