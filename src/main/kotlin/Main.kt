package org.example

import kotlin.random.Random


fun main() {
//    val beverage = Beverage()
    // Фабричный метод
//    val coffee = Coffee()
//    coffee.prepareBeverage()
//    println()
//    val tea = Tea()
//    tea.prepareBeverage()
    val boeing737 = Boeing737()
    for (i in (1..10)) {
        val newPassenger = Passenger(
            name = "name_$i",
            passport = Random.nextInt(1000_000_000).toString(),
            seat = Seat(
                row = Random.nextInt(boeing737.rows),
                letter = (Random.nextInt(boeing737.seatsInRow)+65).toChar()
            )
        )
        boeing737.addPassenger(newPassenger)
    }

    boeing737.getSeatSheme()
}