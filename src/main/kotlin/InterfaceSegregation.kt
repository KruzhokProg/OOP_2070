package org.example

fun main() {
    val d = D.Impl()
    val e = E.Impl()
    val f = F.Impl()
    val g = G.Impl()
    val saveDataSource: DataSource.Save = DataSource.Save.Impl(d, e, f)
    saveDataSource.save()
    val readDataSource: DataSource.Read = DataSource.Read.Impl(f, g)
    readDataSource.read()
    val mutableDataSource: DataSource.Mutable = DataSource.Mutable.Impl(saveDataSource, readDataSource)
}

interface DataSource {

    interface Save {
        fun save()

        class Impl(
            private val d: D,
            private val e: E,
            private val f: F,
        ): Save {
            override fun save() {
                d.d()
                e.e()
                f.f()
            }
        }
    }

    interface Read {
        fun read()

        class Impl(
            private val f: F,
            private val g: G
        ): Read {
            override fun read() {
                f.f()
                g.g()
            }
        }
    }

    interface Mutable: Save, Read {
        class Impl(
            private val save: Save,
            private val read: Read
        ): Mutable {
            override fun save() {
                save.save()
            }

            override fun read() {
                read.read()
            }
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