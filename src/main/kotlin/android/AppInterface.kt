package org.example.android
// interface segregation
interface AppInterface {
    interface Features {
        fun showData()
        fun log(message: String, tag: String = "App")
    }

    interface View {
        fun onResult(result: List<UIDataModel>)
        fun onError(error: Throwable)
    }

}