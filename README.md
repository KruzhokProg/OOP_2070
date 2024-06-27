ДЗ
1
class MyProgressRunnable(
    private val urls: List<String>,
) : Runnable {
    override fun run() {
        val count = urls.size
        val articles = mutableListOf<Article>()
        for (i in 0 until count) {
            val json = Downloader.downloadJson(urls[i])
            articles += parseArticleFromJson(json)
            runOnUiThread(object : Runnable {
                override fun run() {
                    publishProgress(((i.toFloat() / count) * 100).toInt())
                }
            })
        }
        runOnUiThread(object : Runnable {
            override fun run() {
                publishResult(articles)
            }
        })
    }
}

2
fun main() {
    val uiHandler = Handler(Looper.getMainLooper())
    getUser { user ->
        getFeed(user) { feed ->
            uiHandler.post {
                view.showFeed(feed)
            }
        }
    }
}
