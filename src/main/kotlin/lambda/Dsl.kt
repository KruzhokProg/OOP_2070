package org.example.lambda

class Node<T>(
    var data: T? = null,
    @Transient private var root: Node<T>? = null,
    private val children: MutableList<Node<T>> = mutableListOf()
) {
    override fun toString(): String {
        return gson.toJson(this)
    }

    fun <T> Node<T>.node(block: Node<T>.() -> Unit) {
        val node = Node<T>()
        block.invoke(node)
        children.add(node)
        node.root = this
    }
}

fun <T> node(block: Node<T>.() -> Unit): Node<T> {
    val node = Node<T>()
    block.invoke(node)
    return node
}

fun createRootWith2ChildrenDsl(): Node<String> {
    val node: Node<String> = node {
        data = "Root"
        node {
            data = "Child 1"
        }
        node {
            data = "Child 2"
        }
    }
    return node
}

fun createRandomTreeWith2LevelsNew(): Node<String> = node {
    data = "All Companies"
    repeat(random.nextInt(8)) {
        node {
            data = faker.company().name()
            repeat(random.nextInt(8)) {
                node {
                    data = faker.company().name()
                }
            }
        }
    }
}

fun createRandomCompaniesTreeNew(): Node<String> = node {
    data = "Root"
    randomRecursiveNodes(levels = 4)
}

private fun Node<String>.randomRecursiveNodes(levels: Int) {
    if (levels == 0) return
    repeat(random.nextInt(8)) {
        node {
            data = faker.company().name()
            randomRecursiveNodes(levels - 1)
        }
    }
}

fun main() {

//    println(createRootWith2ChildrenDsl())
    println(createRandomCompaniesTreeNew())
}
// DSL - createRandomTreeWithRandomLevels