import org.example.NumbersImpl
import org.junit.jupiter.api.Assertions.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class NumbersImplTest {

    @Test
    fun `check isOverflow by sumInt`() {
        val numbers = NumbersImpl(Int.MAX_VALUE, 1)
        assertThrows(IllegalStateException::class.java) {
            numbers.sumInt()
        }
    }

    @Test
    fun `check isOverflow by sumLong`() {
        val numbers = NumbersImpl(Int.MAX_VALUE, 1)
        val actual = numbers.sumLong()
        val expected = Int.MAX_VALUE.toLong() + 1
        assertEquals(expected, actual)
    }
}