package org.example

import kotlin.math.abs

interface Converter {
    fun convert(value: Double): Double
    fun convert(value: Int): Double
}

abstract class CurrencyConverter : Converter {
    private var result: Double? = null
    abstract val currencyCode: String
    abstract val rate: Double
    override fun convert(value: Double): Double {
        if (rate == 0.0 && value == 0.0) throw ArithmeticException("Деление на 0 на 0 неопределенно")
        if (rate == 0.0) throw ArithmeticException("Деление на 0 запрещено")
        result = value / rate
        return result!!
    }

    override fun convert(value: Int): Double {
        return convert(value.toDouble())
    }

    override fun toString(): String {
        return super.toString() + " " + if (result == null) "Give money" else "$currencyCode: $result"
    }
}

object UsdCoverter : CurrencyConverter() {
    override val rate: Double
        get() = 93.0
    override val currencyCode: String get() = "USD"
}

object EurConverter : CurrencyConverter() {
    override val currencyCode: String
        get() = "EUR"
    override val rate: Double
        get() = 100.0
}

object Converters {
    private val converterList = listOf<CurrencyConverter>(UsdCoverter, EurConverter)
    fun get(currencyCode: String): CurrencyConverter =
        converterList.firstOrNull { it.currencyCode == currencyCode }
            ?: object : CurrencyConverter() {
                override val currencyCode: String
                    get() = "Not found"
                override val rate: Double
                    get() = 1.0
            }
}



fun main() {
    println(Converters.get("EUR").convert(200).toString())
    println(Converters.get("USD").convert(200).toString())
    println(Converters.get("DJF").convert(200).toString())
//    val numbers: Numbers = NumbersImpl(10, 20)
//    numbers.sumInt()
//    val moneyInRub = readln().toInt()//    val usdConverter1 = UsdCoverter
//    val usdConverter2 = UsdCoverter//    println(usdConverter1.toString())
//    println(usdConverter2.toString())
//    usdConverter.convert(moneyInRub)//    println(usdConverter.toString())
////    val eurConverter = EurConverter()
//    eurConverter.convert(moneyInRub)//    println(eurConverter.toString())
}