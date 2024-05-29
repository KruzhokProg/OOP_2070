package org.example.binary_tree

sealed class Tree<out T : Comparable<@UnsafeVariance T>> {
    data object Empty : Tree<Nothing>()
    data class Node<out T : Comparable<@UnsafeVariance T>>(val data: T, var left: Tree<@UnsafeVariance T> = Empty, var right: Tree<@UnsafeVariance T> = Empty) : Tree<T>()
}

//fun <T: Comparable<T>> insert(root: Tree<T>, value: T): Tree.Node<T> {
//
//}

fun <T : Comparable<T>> contains(tree: Tree<T>, item: T): Boolean = when (tree) {
    Tree.Empty -> false
    is Tree.Node -> when {
        item < tree.data -> contains(tree.left, item)
        item > tree.data -> contains(tree.right, item)
        else -> true
    }
}

open class TestUser(val name: String) : Comparable<TestUser> {
    override fun compareTo(other: TestUser): Int {
        return name.compareTo(other.name)
    }
}

class TestEmployee(name: String, val grade: Int, val salary: Double): TestUser(name)

fun main() {
    val tree: Tree.Node<Int> = Tree.Node(
        data = 6,
        left = Tree.Node(3, right = Tree.Node(4)),
        right = Tree.Node(9)
    )

    val employeeTree = Tree.Node(
        TestEmployee("Nick", 8, 120000.0),
        left = Tree.Node(TestEmployee("Ivan", 9, 170000.0), right = Tree.Node(TestEmployee("Peter", 10, 200000.0))),
        right = Tree.Node(TestEmployee("Oleg", 11, 250000.0))
    )

    val userTree: Tree.Node<TestUser> = employeeTree

    for (data in listOf(TestEmployee("Ivan", 9, 170000.0))) {
        println(contains(employeeTree, data))
    }
//    for (item in listOf(6, 3, 4, 0)) {
//        println(contains(tree, item))
//    }
}