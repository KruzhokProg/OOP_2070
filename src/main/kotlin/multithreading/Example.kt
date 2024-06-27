package org.example.multithreading

import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

class MyRunnable: Runnable {
    override fun run() {
        for (i in 0..1_000) {
            println("$i in {${Thread.currentThread().name}}")
        }
    }
}

//fun main() {
//    val myRunnable = MyRunnable()
////    myRunnable.run()
//    val myRunnableThread = Thread(myRunnable)
//    val myRunnableThread2 = Thread(myRunnable)
////    val myRunnbaleThread3 = Thread { TODO("Not yet implemented") }
//    myRunnableThread.start()
//    myRunnableThread2.start()
//}

class State(
    @Volatile
    var x: Int = 0,
    @Volatile
    var y: Int = 0
)

//fun main() {
//    val state = State()
//
//    val t1 = thread {
//        state.x = 1
//        println("y = ${state.y}")
//    }
//    val t2 = thread {
//        state.y = 1
//        println("x = ${state.x}")
//    }
//    t1.join()
//    t2.join()
//}

// {x=1, y=0}
// {y=0, x=1}
// {x=1, y=1}
// {x=0, y=0}

// Атомарность операций
//fun main() {
//    var counter = AtomicInteger(0)
//
//    val t1 = thread {
//        repeat(1_000_000) {
//            counter.incrementAndGet()
//        }
//    }
//    val t2 = thread {
//        repeat(5_000_000) {
//            counter.incrementAndGet()
//        }
//    }
//    t1.join()
//    t2.join()
//    println("$counter")
//}

class Printer {
    fun printPages() {
        for (i in 1..5) {
            println("Printing page --- $i")
        }
    }
}

class PrinterThread(private val threadName: String, val printer: Printer): Thread() {
    private val thread: Thread =  Thread(this, threadName)
    override fun run() {
        synchronized(printer) {
            printer.printPages()
        }
        println("Thread $threadName end")
    }

    override fun start() {
        println("Starting $threadName")
        thread.start()
    }
}

//fun main() {
//    val printer = Printer()
//    val printerThread1 = PrinterThread("Thread - 1", printer)
//    val printerThread2 = PrinterThread("Thread - 2", printer)
//    printerThread1.start()
//    printerThread2.start()
//}

class Singleton private constructor() {

    companion object {
        @Volatile
        private var instance: Singleton? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: Singleton().also { instance = it }
//            instance = Singleton()
        }
    }
}

//fun main() {
//
//    var singleton1: Singleton? = null
//    var singleton2: Singleton? = null
//    val t1 = thread { singleton1 = Singleton.getInstance() }
//    val t2 = thread { singleton2 = Singleton.getInstance() }
//    t1.join()
//    t2.join()
//    println(singleton1)
//    println(singleton2)
//}

//ДЗ
//1
//class MyProgressRunnable(
//    private val urls: List<String>,
//) : Runnable {
//    override fun run() {
//        val count = urls.size
//        val articles = mutableListOf<Article>()
//        for (i in 0 until count) {
//            val json = Downloader.downloadJson(urls[i])
//            articles += parseArticleFromJson(json)
//            runOnUiThread(object : Runnable {
//                override fun run() {
//                    publishProgress(((i.toFloat() / count) * 100).toInt())
//                }
//            })
//        }
//        runOnUiThread(object : Runnable {
//            override fun run() {
//                publishResult(articles)
//            }
//        })
//    }
//}

//2
//fun main() {
//    val uiHandler = Handler(Looper.getMainLooper())
//    getUser { user ->
//        getFeed(user) { feed ->
//            uiHandler.post {
//                view.showFeed(feed)
//            }
//        }
//    }
//}