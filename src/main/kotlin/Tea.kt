package org.example

class Tea : Beverage() {
    override val temp_brew: Float
        get() = 100f

    override fun brew() {
        println("Steeping the tea")
    }

    override fun addCondiments() {
        println("Adding Lemon")
    }
}