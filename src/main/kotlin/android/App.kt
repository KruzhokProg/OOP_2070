package org.example.android
//TDD - Test Driven Development
//DDD - Data Driven Development
class App(
    private val repository: Repository,
    private val view: AppInterface.View
): AppInterface.Features{
    override fun showData() {
        log("showData")
        try {
            val result = repository.fetchData()

            view.onResult(
                result.map {
                    UIDataModel(
                        id = it.id,
                        value = it.value,
                        uuid = Utils.generateUUID()
                    )
                }
            )

        } catch (err: Throwable) {
            log("error")
            view.onError(err)
        }
    }

    override fun log(message: String, tag: String) {
        println("TAG: $tag Message: $message")
    }
}