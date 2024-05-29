package org.example.delegate

import kotlin.properties.Delegates

// NotNull
class Work() {
    fun start() {
        println("Start working")
    }
}

//class WorkingPerson(private val work: Work? = null) {
//    fun init() {
//        work?.start()
//    }
//}
class WorkingPerson {
    private lateinit var work: Work
    private var age by Delegates.notNull<Int>()
    fun init() {
        println(this::work.isInitialized)
        work = Work()
        println(this::work.isInitialized)
        work.start()
        age = 18
    }
}

//fun main() {
//    val workingPerson = WorkingPerson()
//    workingPerson.init()
//}

// Lazy delegate
class Email(val adress: String)
fun loadEmails(): List<Email> = listOf(Email("ya.ru"))

class Person(val name: String) {

    private var _emails: List<Email>? = null

    val emails: List<Email>
        get() {
            if (_emails === null) {
                _emails = loadEmails()
            }
            return _emails!!
        }
}

class LazyPerson(val name: String) {
    val emails by lazy { loadEmails() }
}

//fun main() {
//    val person = LazyPerson("Vanya")
//    println(person.emails.toString())
//    println(person.emails.toString())
//}

//
fun main() {
    var key: String? by Delegates.observable(null) { _, old, new ->
        println("key changed from $old to $new")
    }

    key = "something"
    key = "other"
    // ДЗ vetoable разобраться с примерами
    // Чистая архитектура Боб Мартин SOLID
    // next theme LAMBDA!!! Pattern(Шаблон) Observable
}