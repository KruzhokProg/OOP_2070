package org.example.multithreading

import java.util.concurrent.*

//val uiHandler = Handler(Looper.getMainLooper()) // для обращения к ui потоку
//val t1 = thread {
//    val feed = api.getFeed()
//    uiHandler.post {
//        view.showFeed(feed)
//    }
//}
//
////onDestroy
//t1.interrupt()

class BackgroundThread : Thread() {
    private val queue: BlockingQueue<Runnable> = LinkedBlockingQueue()
    fun add(r: Runnable) {
        queue.offer(r)
    }

    override fun run() {
        while (true) {
            val r = queue.take()
            r.run()
        }
    }
}

//fun main() {
//    val backgroundThread = BackgroundThread()
//
//    // onCreate
//    backgroundThread.start()
//
//    val uiHandler = Handler(Looper.getMainLooper())
//    val loadFeed = Runnable {
//        val feed = api.getFeed()
//        uiHandler.post {
//            view.showFeed(feed)
//        }
//    }
//    backgroundThread.add(loadFeed)
//
//    // onDestroy
//    backgroundThread.interrupt()
//}

//fun main() {
//    val executor: Executor = Executors.newFixedThreadPool(4)
//    val uiHandler = Handler(Looper.getMainLooper())
//
//    val loadFeed = Runnable {
//        val feed = api.getFeed()
//        uiHandler.post {
//            view.showFeed(feed)
//        }
//    }
//    executor.execute(loadFeed)
//}

//fun main() {
//    val executor: ExecutorService = Executors.newFixedThreadPool(4)
//    val uiHandler = Handler(Looper.getMainLooper())
//
//    val loadFeed = Runnable {
//        val feed = api.getFeed()
//        uiHandler.post {
//            view.showFeed(feed)
//        }
//    }
//
//    val task: Future<*> = executor.submit(loadFeed)
//    task.get()
//
//    task.cancel(true)
//    executor.shutdown()
//}

fun main() {
    val executor: ExecutorService = Executors.newFixedThreadPool(4)
    val uiHandler = Handler(Looper.getMainLooper())
    val futures: MutableList<Future<*>> = mutableListOf()

    val getUser: Future<User> = executor.submit{ api.getUser() }
    val getFeed: Future<Feed> = executor.submit{ api.getFeed() }

    val task = executor.submit {
        try {
            val feed = getFeed.get()
            val user = getUser.get()
            uiHandler.post {
                view.showUserWithFeed(user, feed)
            }
        } catch (e: InterruptedException) {
            uiHandler.post {
                view.showError()
            }
        }
    }

    futures.add(getUser)
    futures.add(getFeed)
    futures.add(task)

//    onDestroy
    futures.forEach { future ->
        future.cancel(true)
    }
}