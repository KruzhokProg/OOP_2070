package org.example.multithreading

import com.google.gson.Gson
import com.google.gson.GsonBuilder


fun getDataFromUrl(url: String): String {return "data from $url"}

data class Article(val url: String, private val data: String)


class Downloader {
    companion object {
        val gson: Gson = GsonBuilder().create()

        fun downloadJson(string: String): String {
            return gson.toJson(object {val url = string; val data = getDataFromUrl(string)})
        }
    }
}


class UITread(val runnable: Runnable): Thread() {
    private val thread = Thread(this, "UI")
    override fun run() {
        synchronized(runnable) {
            runnable.run()
        }
    }

    override fun start() {
        thread.start()
    }
}


fun parseArticleFromJson(json: String): Article {
    return Downloader.gson.fromJson(json, Article::class.java)
}

fun runOnUITread(obj: Runnable) {
    UITread(obj).start()
}


fun publishProgress(out: Any) {
    println(out)
}


class MyProgressRunnable(
    private val urls: List<String>
): Runnable {
    override fun run() {
        val count = urls.size
        val articles = mutableListOf<Article>()

        for (i in 0 until count) {
            val json = Downloader.downloadJson(urls[i])
            articles += parseArticleFromJson(json)
            runOnUITread(object : Runnable {
                override fun run() {
                    publishProgress(((i.toFloat() / count) * 100).toInt())
                }
            })
        }

        runOnUITread(object : Runnable {
            override fun run() {
                publishProgress(articles)
            }
        })
    }
}


fun main() {
    val thread = Thread(MyProgressRunnable(listOf("http://api.site1", "https://api.site2")))
    thread.start()
    thread.join()
}