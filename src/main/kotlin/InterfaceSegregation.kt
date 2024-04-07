package org.example

fun main() {
    val d = D.Impl()
    val e = E.Impl()
    val f = F.Impl()
    val g = G.Impl()
    val dataSource = MutableDataSource.Impl(d, e, f, g)
    dataSource.save()
    dataSource.fetch()
}

interface MutableDataSource {
    fun save()
    fun fetch()

    class Impl(
        private val d: D,
        private val e: E,
        private val f: F,
        private val g: G
    ): MutableDataSource {
        override fun save() {
            d.d()
            e.e()
            f.f()
        }

        override fun fetch() {
            f.f()
            g.g()
        }
    }
}

interface D {
    fun d()
    class Impl: D {
        override fun d() {
            TODO("Not yet implemented")
        }
    }
}

interface E {
    fun e()
    class Impl: E {
        override fun e() {
            TODO("Not yet implemented")
        }
    }
}

interface F {
    fun f()
    class Impl: F {
        override fun f() {
            TODO("Not yet implemented")
        }
    }
}

interface G {
    fun g()
    class  Impl: G {
        override fun g() {
            TODO("Not yet implemented")
        }
    }
}