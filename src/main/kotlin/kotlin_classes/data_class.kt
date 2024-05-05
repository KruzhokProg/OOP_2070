package org.example.kotlin_classes

import javax.xml.crypto.Data

class Task (
    val id: Int,
    val desc: String,
    val priority: Int
): Any() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (id != other.id) return false
        if (desc != other.desc) return false
        if (priority != other.priority) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + desc.hashCode()
        result = 31 * result + priority
        return result
    }

    override fun toString(): String {
        return "Task(id=$id, desc='$desc', priority=$priority)"
    }
}

data class DataTask(
    val id: Int,
    val desc: String,
    val priority: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DataTask

        return id == other.id
    }

    override fun hashCode(): Int {
        return id
    }

}

fun main() {
    val task1 = Task(1, "Убраться", 5)
    val task2 = Task(1, "Убраться", 5)
    println(task1.toString())
//    println(task1 == task2)
    val a = "abc"
    val b = "abc"
    val task3 = Task(task1.id, "dsfsdf", task1.priority)
    println(task1 == task2)

    val tasks = hashMapOf<Task, String>()
    tasks.put(task1, "first")
    tasks.put(task2, "second")
    println(tasks.get(Task(1, "Убраться", 5)))
    println(task1.hashCode())
    println(Task(1, "Убраться", 5).hashCode())

    var dataTask1 = DataTask(1, "jhgdgdrg", 6)
    val dataTask2 = DataTask(1, "Убраться", 5)
    println(dataTask1 == dataTask2)
    println(dataTask1 === dataTask2)
    println(dataTask1)
    dataTask1 = dataTask1.copy(desc = "dfjhgdfggfd")
    dataTask1.component1()
}