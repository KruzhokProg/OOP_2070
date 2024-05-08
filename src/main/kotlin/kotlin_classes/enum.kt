package org.example.kotlin_classes

interface Expr

class Num(val value: Int): Expr
class Sum(val left: Expr, val right: Expr): Expr

fun tmp(expr: Expr) = when(expr) {
    is Num -> {}
    is Sum -> {}
    else -> {}
}

enum class Weekday {
    SUNDAY,
    MONDAY,
    TUESDAY,
    SATURDAY
}

fun Weekday.isWeekend() = this == Weekday.SUNDAY

fun translateWeekday(day: Weekday): String = when (day) {
    Weekday.SUNDAY -> "Понедельник"
    Weekday.MONDAY -> TODO()
    Weekday.TUESDAY -> TODO()
    Weekday.SATURDAY -> TODO()
}

enum class Color(val hex: String) {
    RED("FF0000"),
    GREEN("00FF00"),
    BLUE("0000FF")
}

enum class Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

enum class Rank(val value: Int) {
    TWO(2),
    THREE(3),
    JACK(10),
    QUEEN(11)
}

enum class Card(val rank: Rank, val suit: Suit) {
    QUEEN_OF_SPADES(Rank.QUEEN, Suit.SPADES),
    TWO_OF_SPADES(Rank.TWO, Suit.SPADES)
}

enum class OsEnum(val company: String) {
    Linux("Open-Source") {
        override fun getText(): String {
            return "Linux by $company"
        }
    },
    Windows("Microsoft") {
        override fun getText(): String {
            TODO("Not yet implemented")
        }
    },
    MAC("APPLE") {
        override fun getText(): String {
            TODO("Not yet implemented")
        }
    };

    abstract fun getText(): String
}

fun main() {
    println(Weekday.SUNDAY.ordinal)
    println(Weekday.entries)

    val weekday = "SUNDAY"
//    println(Weekday.valueOf(weekday))
    println(enumValues<Weekday>().firstOrNull { it.ordinal == 0 })
    println(Weekday.SATURDAY.isWeekend())
    println(enumValues<Color>().firstOrNull { it.hex == "00FF00" })
    println(listOf(Card.QUEEN_OF_SPADES, Card.TWO_OF_SPADES).sorted())
    val rankComparator = Comparator<Rank> { r1, r2 -> r1.value - r2.value }
    val cardComparator = Comparator<Card> { c1, c2 -> rankComparator.compare(c1.rank, c2.rank)}
    println(listOf(Card.QUEEN_OF_SPADES, Card.TWO_OF_SPADES).sortedWith(cardComparator))
//    for (item in Weekday.values()) {
//        println("${item.name} ${item.ordinal}")
//    }
}
