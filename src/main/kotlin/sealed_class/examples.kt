package org.example.sealed_class

sealed class Response {
    object Loading: Response()
    data class Success(val data: String): Response()
    data class Error(val exception: Throwable): Response()
}

//sealed class JvmLang {
//    object Java: JvmLang()
//    object Kotlin: JvmLang()
//    object Groovy: JvmLang()
//}
//
//sealed class CompiledLang: JvmLang() {
//    object Cpp: CompiledLang()
//}
//
//sealed class FunctionalLang {
//    object Python: FunctionalLang()
//    object JS: FunctionalLang()
//}
//
//sealed class JvmFunctional: JvmLang() {
//    object Python: FunctionalLang()
//    object JS: FunctionalLang()
//}

sealed interface CompiledLang
sealed interface FunctionalLang
sealed interface InterpretedLang
sealed interface JvmLang: CompiledLang

object Cpp: CompiledLang
object Java: JvmLang
object Kotlin: JvmLang, FunctionalLang
object JS: FunctionalLang, InterpretedLang
object Python: FunctionalLang, InterpretedLang
object Groovy: JvmLang, FunctionalLang, InterpretedLang

fun checkFunctionalLang(lang: CompiledLang) {
    when(lang) {
        Cpp -> TODO()
        Groovy -> TODO()
        Java -> TODO()
        Kotlin -> TODO()
    }
}

fun handleRequest(response: Response) {
    when(response) {
        is Response.Error -> {

        }
        is Response.Success -> {

        }

        Response.Loading -> {

        }
    }
}

fun main() {

}