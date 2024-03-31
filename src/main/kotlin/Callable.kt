package org.example

interface Callable {
    fun call()
}

interface Receivable: Callable {
    fun receive()
}

interface Browsable {
    fun browse()
}

class Phone: Receivable, Browsable {
    override fun receive() {
        println("Телефон получает звонки")
    }

    override fun call() {
        println("Телефон звонит")
    }

    override fun browse() {
        println("Телефон вышел в интернет")
    }
}

class Tablet: Receivable, Browsable {
    override fun receive() {
        println("Планшет принимает звонки")
    }

    override fun call() {
        println("Планшет звонит")
    }

    override fun browse() {
        println("Планшет вышел в интернет")
    }
}

class SmartWatch: Callable, Browsable {
    override fun call() {
        println("Умные часы звонят")
    }

    override fun browse() {
        println("Часы вышел в интернет")
    }
}

class Fridge: Receivable {
    override fun receive() {
        println("Холодильник принимает звонки")
    }

    override fun call() {
        println("Холодильник звонит")
    }
}

fun main() {
    val phone = Phone()
    val tablet = Tablet()
    val smartWath = SmartWatch()
    val fridge = Fridge()
//    phone.call()
//    tablet.call()
//    smartWath.call()
    listOf(phone, tablet, fridge).forEach { it.receive() }
}