package org.example.delegate

interface CarService {
    fun start()
}


open class ServiceDecorator(service: CarService): CarService by service


class InsideCarCleanup(service: CarService): ServiceDecorator(service) {
    override fun start() {
        super.start()
        println("Cleaning car inside")
    }

}

class OutsideCarCleanup(service: CarService): ServiceDecorator(service) {
    override fun start() {
        super.start()
        println("Cleaning car outside")
    }

}

class CarCheckup: CarService {
    override fun start() {
        println("Doing check up")
    }
}


fun main() {
    val carService = InsideCarCleanup(OutsideCarCleanup(CarCheckup()))
    carService.start()
}