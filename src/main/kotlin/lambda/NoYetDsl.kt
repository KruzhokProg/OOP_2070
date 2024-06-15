package org.example.lambda

import com.github.javafaker.Faker
import com.google.gson.GsonBuilder
import java.util.*

// DSL - Domain Specific Language [Jetpack compose]
// GCL - General Common Language

val gson = GsonBuilder()
    .setPrettyPrinting()
    .create()
val random = Random(0)
val faker = Faker(random)

class OldNode<T>(
    @Transient val root: OldNode<T>?,
    val data: T,
    val children: List<OldNode<T>>
) {
    override fun toString(): String {
        return gson.toJson(this)
    }
}

fun createRootWith2Children(): OldNode<String> {
    val rootChildren: MutableList<OldNode<String>> = mutableListOf()
    val rootNode = OldNode(
        root = null,
        data = "Root",
        children = rootChildren
    )

    rootChildren.add(
        OldNode(
            root = rootNode,
            data = faker.company().name(),
            mutableListOf()
        )
    )
    rootChildren.add(
        OldNode(
            root = rootNode,
            data = faker.company().name(),
            mutableListOf()
        )
    )
    return rootNode
}

// createRandomTreeWithRandomLevels -> любое рандомное количество дочерних элементов

fun main() {
    println(createRootWith2Children())
}
