package org.example

abstract class Callable {
    abstract fun call()
}

abstract class CallableAndBrowsable: Callable() {
    abstract fun browse()
}

abstract class CallableAndBrowsableAndReceivable: CallableAndBrowsable() {
    abstract fun receive()
}

class Phone: CallableAndBrowsable() {
    override fun call() {
        println("Телефон звонит")
    }

    override fun browse() {
        println("Телефон вышел в интернет")
    }
}

class Tablet: CallableAndBrowsable() {
    override fun call() {
        println("Планшет звонит")
    }

    override fun browse() {
        println("Планшет вышел в интернет")
    }
}

class SmartWatch: CallableAndBrowsable() {
    override fun call() {
        println("Умные часы звонят")
    }

    override fun browse() {
        println("Часы вышел в интернет")
    }
}

class Fridge: Callable() {
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
    listOf(phone, tablet, smartWath, fridge).forEach { it.call() }
}