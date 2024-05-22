package org.example.delegate

import java.util.Base64
import kotlin.reflect.KProperty

interface Player {
    fun playGame()
}

class RpgGamePlayer(val enemy: String): Player {
    override fun playGame() {
        println("Killing $enemy")
    }
}
//class WitcherPlayer(enemy: String): RpgGamePlayer(enemy)
//class WitcherPlayer(enemy: String): Player {
//    val player = RpgGamePlayer(enemy)
//    override fun playGame() {
//        player.playGame()
//    }
//}
class WitcherPlayer(enemy: String): Player by RpgGamePlayer(enemy)

//fun main() {
//    RpgGamePlayer("monsters").playGame()
//    WitcherPlayer("monsters").playGame()
//}
//---------------------------------------
// несколько интерфейсов
interface GameMaker {
    fun developGame()
}

interface Developer {
    fun playGame()
}

class RpgCreator(val gameName: String): GameMaker {
    override fun developGame() {
        println("Making $gameName")
    }
}

class RpgPlayerAndMaker: Player by RpgGamePlayer("monsters"), GameMaker by RpgCreator("Diablo 3") {
    fun test() {
        playGame()
        developGame()
    }
}

//fun main() {
//    val a = RpgPlayerAndMaker()
//    a.test()
//}
// Decorator Pattern
interface Component {
    fun sayHello(): String
}

class ConcreteCompopent1: Component {
    override fun sayHello() = "hello from ${javaClass.simpleName}"
}

abstract class Decorator(val component: Component): Component by component

class Decorator1(component: Component): Decorator(component) {
    override fun sayHello(): String {
        return "${super.sayHello()} and from ${javaClass.simpleName}"
    }
}

class Decorator2(component: Component): Decorator(component) {
    override fun sayHello(): String {
//        return super.sayHello() + " and from ${javaClass.simpleName}"
        return "${component.sayHello()} and from ${javaClass.simpleName}"
    }
}

//fun main() {
//    val first: Component = ConcreteCompopent1()
//    val decOne: Component = Decorator1(first)
//    val decTwo: Component = Decorator2(decOne)
//    println(first.sayHello())
//    println(decOne.sayHello())
//    println(decTwo.sayHello())
//}
//---------------------------------------
sealed class MessageResult {
    object Success: MessageResult()
    object Error: MessageResult()
}

interface Communication {
    fun sendMessage(text: String): MessageResult
}

class BtComm: Communication {
    override fun sendMessage(text: String): MessageResult {
        println("Sending message via BT: $text")
        return MessageResult.Success
    }
}

class TcpComm: Communication {
    override fun sendMessage(text: String): MessageResult {
        println("Sending message via Tcp: $text")
        return MessageResult.Success
    }
}

abstract class CommunicationDecorator(communication: Communication): Communication by communication

class JsonDecorator(communication: Communication): CommunicationDecorator(communication) {
    override fun sendMessage(text: String): MessageResult {
        return super.sendMessage("{\"message\":\"$text\"}")
    }
}

class Base64Decorator(communication: Communication): CommunicationDecorator(communication) {
    override fun sendMessage(text: String): MessageResult {
        return super.sendMessage(prepareMessage(text))
    }

    private fun prepareMessage(text: String): String {
        return Base64.getEncoder().encodeToString(text.toByteArray())
    }
}

//fun main() {
//    val message = "Hello"
//    val tcpJsonComm: Communication = JsonDecorator(TcpComm())
//    tcpJsonComm.sendMessage(message)
//    val tcpJsonBase64Comm = JsonDecorator(Base64Decorator(TcpComm()))
//    tcpJsonBase64Comm.sendMessage(message)
//    val tcpBase64JsonComm = Base64Decorator(JsonDecorator(TcpComm()))
//    tcpBase64JsonComm.sendMessage(message)
//}
// ДЗ:
//  val carService = InsideCarCleanup(CarWash(CarCheckUp()))
//  carService.do()
// Doing check up ... DONE
// Washing car ... DONE
// Cleaning car inside ... DONE

var token: String? = null
    get() {
        println("token getter returned $field")
        return field
    }
    set(value) {
        println("token changed from $field to $value")
        field = value
    }

var attempts: Int = 0
    get() {
        println("attempts getter returned $field")
        return field
    }
    set(value) {
        println("attempts changed from $field to $value")
        field = value
    }

class LogginProperty<T>(var value: T) {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): T {
        println("${prop.name} getter returned $value")
        return value
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, newValue: T) {
        println("${prop.name} changed from $value to $newValue")
        value = newValue
    }
}

fun main() {
    var token: String? by LogginProperty(null)
    var attempts: Int by LogginProperty(0)
    token = "AAA"
    val res = token
    println(res)
    attempts++
}
