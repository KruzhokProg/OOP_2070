package kotlin_classes

import org.example.kotlin_classes.Task
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TaskTest {

    @Test
    fun `check ordinary class when equals and hashcode override`() {
        //arrange
        val task1 = Task(1, "Убраться", 5)
        val task2 = Task(1, "Убраться", 6)
        //assert
        assert(task1 != task2)
        assert(task1 === task1.copy(desc = "fdmghdfg"))
    }

}