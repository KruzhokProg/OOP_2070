package org.example

class Coffee : Beverage() {
    override val temp_brew: Float
        get() = 80.5f

    override fun brew() {
        println("Dripping Coffee through filter")
    }

    override fun addCondiments() {
        println("Adding Sugar and Milk")
    }
}