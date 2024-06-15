package org.example.lambda

//fun greeting(name: String) {
//    println("Hello $name")
//}

fun doOperation(x: Int, y: Int, op: (Int, Int) -> Int) {
    println(op(x, y))
}

fun doOperationWithoutLambda(x: Int, y: Int, operation: Operation) {
    println(operation.invoke(x, y))
}

fun doOperationWithoutLambda(x: Int, y: Int, operation: Function2<Int, Int, Int>) {
    println(operation.invoke(x, y))
}

interface SumOperation : Operation

interface Operation {
    fun invoke(x: Int, y: Int): Int
}

typealias CommonOperation = (x: Int, y: Int) -> Int

fun main2() {
//    greeting("Peter")
    val greeting = { println("Hello") }
    greeting.invoke()
    val greetingByName = { name: String -> println("Hello $name") }
//    greetingByName("Peter")
    val printSum = { x: Int, y: Int -> println(x + y) }
    val sum = { x: Int, y: Int -> x + y }
    val sum2: CommonOperation? = null
    lateinit var sum3: CommonOperation
    val sumWithLogging = { x: Int, y: Int ->
        val res = x + y
        println("$x + $y = $res")
        res
    }
    sumWithLogging(2, 3)
    doOperation(3, 4, sum)
    doOperation(3, 4, op = { x: Int, y: Int -> x + y })

    val sumOperation = object : Operation {
        override fun invoke(x: Int, y: Int): Int {
            return x + y
        }
    }
    val minusOperation = object : Operation {
        override fun invoke(x: Int, y: Int): Int {
            return x - y
        }
    }
    val multiplyOperation = object : Function2<Int, Int, Int> {
        override fun invoke(p1: Int, p2: Int): Int {
            return p1 * p2
        }

    }
    doOperationWithoutLambda(3, 4, sumOperation)
    doOperationWithoutLambda(3, 4, minusOperation)
    doOperationWithoutLambda(3, 4, multiplyOperation)


}

// ДЗ: написать конвертер
// convert(x = 20.0, converter = {t -> t * 1.8 + 32})

// Возврат функции из функции (аналог паттерна стратегия)

enum class Action {
    PLUS,
    MINUS,
    MULTIPLY
}

fun selectAction(action: Action): (Int, Int) -> Int {
    return when (action) {
        Action.PLUS -> { x, y -> x + y }
        Action.MINUS -> { x, y -> x - y }
        Action.MULTIPLY -> { x, y -> x * y }
    }
}

//fun main() {
//    val action = selectAction(Action.MULTIPLY)
//    println(action(3, 4))
//}

//data class Person(
//    val firstName: String,
//    val lastName: String,
//    val phoneNumber: String? = null
//)
//
//class ContactListFilter(var prefix: String = "", var onlyWithPhoneNumber: Boolean = false) {
//    fun getPredicate(): (Person) -> Boolean {
//        val startsWithPrefix = { p: Person -> p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix) }
//        if (!onlyWithPhoneNumber) return startsWithPrefix
//        return { startsWithPrefix(it) && it.phoneNumber != null }
//    }
//}

//fun main() {
//    val contacts = listOf(
//        Person("Антон", "Морозов", "123-456"),
//        Person("Алексей", "Морозов",),
//        Person("Николай", "Иртуганов", "654-321"),
//        Person("Петр", "Петров"),
//    )
//
//    val contactListFilter = ContactListFilter(prefix = "А", onlyWithPhoneNumber = true)
//    println(contacts.filter(contactListFilter.getPredicate()))
//}

// ДЗ
//fun main() {
//    val button = Button()
//    val textView = TextView()
//    button.setOnClickListener(object : OnClickListener {
//        override fun onClick(view: View) {
//            if (view is TextView) {
//                view.setText("Hello")
//            }
//        }
//    })
//
//    button.setOnClickListener {
//        textView.setText("Hello")
//    }
//}

// Ссылочный тип
data class Person(val name: String, val age: Int)
//fun sendEmails(p: Person, message: String) = Unit
//fun some(param: (Person, String) -> Unit) = Unit
//fun main() {
//    val people = listOf(Person("Alice", 29), Person("Bob", 31))
////    println(people.maxBy { p:Person -> p.age })
////    println(people.maxBy { it.age })
//    println(people.maxBy (Person::age))
////    val action = { p: Person, message: String -> sendEmails(p, message) }
//    some(::sendEmails)
//}

// filter

//fun main() {
//    val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Peter", 31))
//    println(people.filter { it.age > 30 })
//    println("hjagsdasd".myFilter { it > 'a' })
//}

fun String.myFilter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element)) sb.append(element)
    }
    return sb.toString()
}
// ДЗ: реализовать joinToString
// попробовать сделать filter над Iterable

// Встроенные функции

class Book(val title: String, val authors: List<String>)

fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\nAlphabet is ready")
    return result.toString()
}

fun alphabe2(): String = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nAlphabet is ready")
    toString()
}

fun alphabe3() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nAlphabet is ready")
}

fun alphabe4() = StringBuilder().run {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nAlphabet is ready")
}

//fun main() {
//    val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Peter", 31))
//    val allLess30 = people.all { it.age < 30 }
//    println(allLess30)
//    val anyLess30 = people.any { it.age < 30 }
//    println(people.count { it.age < 30 })
//    println(people.find { it.age < 30 })
//    println(people.groupBy { it.age < 30 })
//
//    val books = listOf(
//        Book("Thursday Next", listOf("Jasper Fforde")),
//        Book("Mort", listOf("Terry Pratchett")),
//        Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman"))
//    )
//    println(books.flatMap { it.authors }.toSet())
//}

// функции высшего порядка
enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID }
data class SiteVisit(val path: String, val duration: Double, val os: OS)
val log = listOf(
    SiteVisit("/", 34.0, OS.WINDOWS),
    SiteVisit("/", 22.0, OS.MAC),
    SiteVisit("/login", 12.0, OS.WINDOWS),
    SiteVisit("/signup", 8.0, OS.IOS),
    SiteVisit("/", 16.3, OS.ANDROID)
)

fun List<SiteVisit>.averageDurationFor(predicate: (SiteVisit) -> Boolean) = filter(predicate).map(SiteVisit::duration).average()
//fun main() {
//    val average = log.filter { it.os == OS.WINDOWS }.run { sumOf { it.duration }/size }
//    println(average)
//    val averageWindowsDuration = log.filter { it.os == OS.WINDOWS }.map(SiteVisit::duration).average()
//    println(averageWindowsDuration)
//    println(log.averageDurationFor{ it.os == OS.WINDOWS && it.path == "/" })
//    println(log.averageDurationFor{ it.os == OS.MAC })
//}

class User(
    val age: Int,
    val name: String
)

fun spamLambdas(users: List<User>) = users
    .filter { it.age > 18 }
    .groupBy { it.name }
    .mapValues { it.value.size }
    .entries
    .sortedBy { it.value }

// Sequence

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Peter", 31))
//    println(people.map(Person::name).filter { it.startsWith("A") })
//    println(people.asSequence().map(Person::name).filter { it.startsWith("A") }.toList())
    listOf(1, 2, 3, 4).asSequence().map { println("map($it) "); it * it }.filter { println("filter($it) "); it % 2 == 0 }.toList()
    println()
    println()
    listOf(1, 2, 3, 4).map { println("map($it) "); it * it }.filter { println("filter($it) "); it % 2 == 0 }.toList()
}



























