package org.example.android

interface Repository {

    fun fetchData(): List<Model>
}