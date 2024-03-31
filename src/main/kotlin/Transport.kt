package org.example

abstract class Transport {
    abstract val capacity: Int
    abstract fun move()
    abstract fun getSeatSheme()
}

class CargoTrain: Transport() {
    override val capacity: Int
        get() = 1000

    override fun move() {
        println("Поезд доставляет груз")
    }

    override fun getSeatSheme() {}
}

abstract class Aircraft: Transport() {
    abstract val model: String
    abstract val engineCount: Int
    abstract val altitude: Int

    abstract val numberOfPasses: Int
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

    override val capacity: Int
        get() = rows * seatsInRow

    override fun getSeatSheme() {
        println(" ABC DEF")
        seatScheme.forEachIndexed { row, seats ->
            print("${row + 1} ")
            seats.forEachIndexed { index, passenger ->
                if (passenger == null) {
                    print("_")
                } else {
                    print("X")
                }
                if ((index + 1) % ((seats.size) / (numberOfPasses+1)) == 0) {
                    print(" ")
                }
            }
            println()
        }
    }
}

class Boeing737: Aircraft(){
    override val model: String
        get() = "Boeing"
    override val engineCount: Int
        get() = 2
    override val altitude: Int
        get() = 12500
    override val numberOfPasses: Int
        get() = 3
    override val rows: Int
        get() = 10
    override val seatsInRow: Int
        get() = 12

// ДЗ:
//  AB CD EF
//1 _X _X X_
//2 __ X_ __
//3 __ _X XX

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