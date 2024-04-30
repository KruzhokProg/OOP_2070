
import org.example.Numbers
import org.example.QuadrantDifferenceImpl
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class QuadrantDifferenceImplTest {

    @Test
    fun `test QuadrantDifference when sumInt overflow`() {
        val numbers = FakeNumbers.Impl()
        val quadrantDifference = QuadrantDifferenceImpl(numbers)
        val actual = quadrantDifference.value()
        val expected = 15L
        assertEquals(expected, actual)
        assertEquals(true, numbers.differenceCalled())
        assertEquals(true, numbers.sumIntCalled())
    }
}

interface FakeNumbers: Numbers {
    fun differenceCalled(): Boolean
    fun sumIntCalled(): Boolean

    class Impl: FakeNumbers {

        private var differenceCalled = false
        private var sumIntCaleed = false
        override fun difference(): Int {
            differenceCalled = true
            return 3
        }

        override fun divide(): Double {
            TODO("Not yet implemented")
        }

        override fun isOverflow(): Boolean {
            TODO("Not yet implemented")
        }

        override fun sumInt(): Int {
            sumIntCaleed = true
            return 5
        }

        override fun differenceCalled(): Boolean {
            return differenceCalled
        }

        override fun sumIntCalled(): Boolean {
            return sumIntCaleed
        }
    }
}