package org.example

abstract class Beverage {

    abstract protected val temp_brew: Float
    // Template method for preparing a beverage
    fun prepareBeverage() {
        boilWater()
        brew()
        pourInCup()
        addCondiments()
    }

    protected fun boilWater() {
        println("Boiling water")
    }

    protected abstract fun brew()

    protected fun pourInCup() {
        println("Pouring into cup")
    }

    protected abstract fun addCondiments()
}