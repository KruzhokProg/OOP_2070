package org.example.generics

import org.example.kotlin_classes.Num
import java.math.BigDecimal

class ValueWithHistory<T>(
    private var value: T
) {
    private var history: List<T> = listOf(value)

    fun setValue(value: T) {
        this.value = value
        history += value
    }

    fun currentValue(): T = value

    fun history(): List<T> = history
}

//fun main() {
//    val valueWithHistory = ValueWithHistory("1")
//    valueWithHistory.setValue("2")
//    val value = valueWithHistory.currentValue()
//    val history = valueWithHistory.history()
//    println("value=$value\nhistory=$history")
//    val valueWithHistory2 = ValueWithHistory(1)
//    valueWithHistory2.setValue(3)
//    val value2 = valueWithHistory2.currentValue()
//    val history2 = valueWithHistory2.history().map {it * 2  }
//    println("value=$value2\nhistory=$history2")
//}

class SimpleList<T: Number>
fun <T: Comparable<T>> maxOf(a: T, b: T): T {
    return if (a>b) a else b
}

//class Box<T>
//{
//    var value: T? = null
//    fun get(): T = value!!
//}
//
//interface Animal {
//    fun feed()
//}
//
//interface Good {
//    fun pet()
//}
//
//fun <T> pet(animal: T) where T: Animal, T: Good{
//    animal.feed()
//    animal.pet()
//}

//fun main() {
//    val cat = object : Animal, Good {
//        override fun feed() {
//            TODO("Not yet implemented")
//        }
//
//        override fun pet() {
//            TODO("Not yet implemented")
//        }
//    }
//    val rat = object : Animal {
//        override fun feed() {
//            TODO("Not yet implemented")
//        }
//    }
//    pet(cat)
////    pet(rat)
////    maxOf(1, 2)
////    maxOf(1.0, 2.0)
////    println(maxOf("a", "b"))
////    val a = SimpleList<Int>()
////    val b = SimpleList<Double>()
////    val bc = SimpleList<Float>()
////    val bcd = SimpleList<BigDecimal>()
////    val c = SimpleList<String>()
//}

interface StackInterface<T> {
    // LIFO - last In First Out
    // FILO - First In |ast out
    fun push(item: T)
    fun pop(): T?
    fun peek(): T?
    fun isEmpty(): Boolean
}

class StackImpl<T>: StackInterface<T> {
    private val elements = mutableListOf<T>()
    override fun push(item: T) {
        elements.add(item)
    }
    override fun pop(): T? = elements.removeLastOrNull()
    override fun peek(): T? = elements.lastOrNull()

    override fun isEmpty(): Boolean  = elements.isEmpty()
}

//fun main() {
//    val stack = StackImpl<Int>()
//    stack.push(1)
//    stack.push(2)
//    stack.pop()
//    stack.pop()
//    stack.pop()
//    stack.peek()
//}


//inline fun <reified T> example(a: Any): Boolean {
//    return a is T
//}
//
//fun main() {
//    println(example<String>("a"))
//    println(example<String>(2))
//}

//class User(val grade: Int)
open class Animal
class Cat: Animal()
class Rat: Animal()
class Box<T: Animal> {
    fun some() {}
}

fun <T: Animal> feedAllAnimal(element:Box<T>) {
    element.some()
}

class Processor<in T: Number> {
    fun process(a: T) {}
}

class Box2<in T: Animal> {
    fun some() {}
}

//fun main() {
//    val b = 2
//    feedAllAnimal(Box<Cat>())
//    val a = listOf(User(10), User(4), User(1))
//    a.sortedWith()
//    val a: Animal = Cat()
//    val b: Box<Animal> = Box<Cat>()
//    val c: List<Number> = listOf<Int>()
//    val numbers = arrayOf(10,15, 20)
//    val objects: Array<Any> = numbers
//    objects[1] = "hjgxfdfg"
//    val p: Processor<Int> = Processor<Number>()
//    val box1: Box2<Cat> = Box2<Animal>()
//}

open class User(var rank: Int)
open class Moderator(rank: Int): User(rank) {
    override fun toString(): String {
        return "Moderatpr(rank = $rank)"
    }
}
class Admin(rank: Int): Moderator(rank)

val userComparator: Comparator<User> = Comparator { u1, u2 -> u1.rank - u2.rank }

interface CustomComparator<T> {
    fun compare(o1: T, o2: T): Boolean
}

val userCustomComparator = object : CustomComparator<User> {
    override fun compare(o1: User, o2: User): Boolean {
        return o1.rank > o2.rank
    }
}

//val moderatorComparator: CustomComparator<in Moderator> = userCustomComparator

fun <T> bubbleSort(values: Array<T>, comp: CustomComparator<in T>) {
    for (i in values.size - 1 downTo 0) {
        for (j in 0 until i) {
            if (comp.compare(values[j], values[j+1])) {
                val tmp = values[j]
                values[j] = values[j+1]
                values[j+1] = tmp
            }
        }
    }
}

fun main() {

    val moderators = arrayOf<Moderator>(Moderator(123), Moderator(4))
    bubbleSort(moderators, userCustomComparator)
    println(moderators.joinToString { it.toString() })
//    val listA: MutableList<User> = mutableListOf()
//    val listB: MutableList<Moderator> = mutableListOf()
//    val listC: MutableList<Admin> = mutableListOf()
//
//    listA.sortedWith(userComparator)
//    listB.sortedWith(userComparator)
}