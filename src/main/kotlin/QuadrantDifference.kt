package org.example

interface QuadrantDifference {
    // a**2 - b**2 = (a-b)*(a+b)
    fun value(): Long
}

class QuadrantDifferenceImpl(private val numbers: Numbers): QuadrantDifference {
    override fun value(): Long {
        return numbers.difference() * numbers.sumInt().toLong()
        // c try catch и (без!!!)
    }
}


