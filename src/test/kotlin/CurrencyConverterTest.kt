
import org.example.CurrencyConverter
import org.junit.jupiter.api.Assertions.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class CurrencyConverterTest {

    @Test
    fun `Проверка успешного выполнения Конвертера`() {
        //arrange
        val converter = TestCurrencyConverter(100.0)
        //act
        val actual = converter.convert(200)
        val expected = 2.0
        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun `Проверка Конвертера с rate = 0`() {
        val converter = TestCurrencyConverter(0.0)
        assertThrows(ArithmeticException::class.java) {
            converter.convert(100)
        }
    }

    @Test
    fun `Проверка Конвертера с rate = 0 и sum = 0`() {
        val converter = TestCurrencyConverter(0.0)
        assertThrows(ArithmeticException::class.java) {
            converter.convert(0)
        }
    }

}

class TestCurrencyConverter(private val currencyRate: Double): CurrencyConverter() {
    override val currencyCode: String
        get() = "Test"
    override val rate: Double
        get() = currencyRate
}