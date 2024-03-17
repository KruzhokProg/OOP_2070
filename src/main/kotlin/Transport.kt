package org.example

abstract class Transport {
    abstract val capacity: Int
    abstract fun move()
}

class CargoTrain: Transport() {
    override val capacity: Int
        get() = 1000

    override fun move() {
        println("Поезд доставляет груз")
    }
}

abstract class Aircraft: Transport() {
    abstract val model: String
    abstract val engineCount: Int
    abstract val altitude: Int

    abstract val rows: Int
    abstract val seatsInRow: Int

    protected val seatScheme = List(rows) {
        MutableList<Passenger?>(seatsInRow){
            null
        }
    }

    fun addPassenger(passenger: Passenger) {
        val row = passenger.seat.row
        val letter = passenger.seat.letter
        seatScheme[row][letter.uppercaseChar() - 'A'] = passenger
    }

    fun getPassenger(seat: Seat): Passenger? {
        val row = seat.row
        val letter = seat.letter
        return seatScheme[row][letter.uppercaseChar() - 'A']
    }

    override fun move() {
        println("Летит")
    }

    abstract fun getSeatSheme()
}

class Boeing737: Aircraft(){
    override val model: String
        get() = "Boeing"
    override val engineCount: Int
        get() = 2
    override val altitude: Int
        get() = 12500
    override val rows: Int
        get() = 10
    override val seatsInRow: Int
        get() = 6

    override fun getSeatSheme() {
        // ABC DEF
        //1 _X_ XX_
        //2 __X ___
        //3 ___ XXX
        println(" ABC DEF")
        seatScheme.forEachIndexed { row, seats ->
            print("${row + 1} ")
            seats.forEachIndexed { index, passenger ->
                if (passenger == null) {
                    print("_")
                } else {
                    print("X")
                }
                if (index == (seats.size-1) / 2) {
                    print(" ")
                }
            }
            println()
        }
    }
// ДЗ:
//  AB CD EF
//1 _X _X X_
//2 __ X_ __
//3 __ _X XX
    override val capacity: Int
        get() = rows * seatsInRow


}

class Passenger(
    val name: String,
    val passport: String,
    val seat: Seat
)

class Seat(
    val row: Int,
    val letter: Char // A, B, C, D, E, F
)