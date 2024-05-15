package org.example.companion_object

import com.sun.tools.javac.Main

class Person(val name: String) {

    init {
        counter++
    }

    companion object {
        var counter = 0
    }

}

interface Bundle

abstract class Activity {
    abstract fun onCreate(savedInstanceState: Bundle)
}

// A : B -> Class<A> : Class<B> (не работает)

class Intent(cls: Class<out Any>) {
    fun putExtra(name: String, value: Any) = Unit
}

class Context {
    fun startActivity(intent: Intent) {

    }
}
// ДЗ
// Activity -> Fragment1, Fragment2
// Single Activity
// val activity = Activity()
// Stack -> listStackOf()
// Activity.FragmentManager.addFragment(fr1)
// Activity.FragmentManager.addFragment(fr2)
// Activity.FragmentManager.getFragment().show() -> Fragment()
// Activity.FragmentManager.getFragment().pop()
// activity

interface ActivityFactory {
     fun getIntent(context: Context): Intent

    fun start(context: Context) {
        val intent = getIntent(context)
        context.startActivity(intent)
    }
}

class OtherActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle) {
        TODO("Not yet implemented")
    }

    companion object: ActivityFactory {
        private const val PARAM_NAME = "param"
        override fun getIntent(context: Context): Intent {
            val intent = Intent(OtherActivity::class.java)
            intent.putExtra(PARAM_NAME, "args")
            return intent
        }

    }
}

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val PARAM_NAME = "param"

        fun newIntent(): Intent {
            val intent = Intent(MainActivity::class.java)
            intent.putExtra(PARAM_NAME, "args")
            return intent
        }
    }
}

fun main() {

    val context = Context()
    context.startActivity(MainActivity.newIntent())
    val otherActivity = OtherActivity()
//    otherActivity.start(context)

    val person1 = Person("a")
//    person1.counter
    println(Person.counter)
//    val person2 = Person("b")
//    println(Person.counter)
//    val person3 = Person("c")
//    println(Person.counter)
}