package org.example

interface Numbers {
    fun difference(): Int
    fun divide(): Double
    fun isOverflow(): Boolean
    fun sumInt(): Int
//    fun sumLong(): Long
}

class NumbersImpl(
    private val number1: Int,
    private val number2: Int
) : Numbers {

    private var wasOverflowSum = false
    private var wasOverflowDiff = false

    init {
        isOverflow()
    }

    override fun difference(): Int {
        if (wasOverflowDiff) throw IllegalStateException("Overflow")
        return number1 - number2
    }

    override fun divide(): Double {
        if (number1 == 0 && number2 == 0) throw ArithmeticException("0/0 is undefined")
        if (number2 == 0) throw ArithmeticException("division by 0 is illegal")
        return number1.toDouble() / number2
    }

    override fun isOverflow(): Boolean {
        val sum = number1.toLong() + number2
        val diff = number1.toLong() - number2
        wasOverflowSum = (sum > Int.MAX_VALUE || sum < Int.MIN_VALUE)
        wasOverflowDiff = (diff > Int.MAX_VALUE || diff < Int.MIN_VALUE)
        return wasOverflowSum && wasOverflowDiff // !(a || b) = !a && !b
    }

    override fun sumInt(): Int {
        if (wasOverflowSum) throw IllegalStateException("Overflow")
        return number1 + number2
    }

//    override fun sumLong(): Long {
//        return number1.toLong() + number2
//    }
}