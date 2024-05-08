package android

import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.example.android.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AppTest {

    @RelaxedMockK
    lateinit var view: AppInterface.View

    @RelaxedMockK
    lateinit var repository: Repository

    lateinit var app: AppInterface.Features

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        app = App(repository, view)
    }

    @Test
    fun `Fetches data and throws exception`() {
        // arrange
        every { repository.fetchData() } throws IllegalStateException()
        //act
        app.showData()
        //assert
        verify(exactly = 1) { view.onError(any()) }
        verify(exactly = 0) { view.onResult(any()) }
    }

    @Test
    fun `Fetches data when success`() {
        mockkObject(Utils)
        val uuid = "fake-uuid"
        val id = 1
        val value = "fake-value"
        every { repository.fetchData() } returns listOf(Model(id, value))
        every { Utils.generateUUID() } returns uuid
        app.showData()
        val captureSlot = slot<List<UIDataModel>>()
        verify(exactly = 1) { view.onResult(capture(captureSlot)) }

        captureSlot.captured.let {
            assert(it.isNotEmpty())
            assertEquals(uuid, it.first().uuid)
            assertEquals(id, it.first().id)
            assertEquals(value, it.first().value)
        }
        unmockkObject(Utils)
    }

    //ДЗ
    @Test
    fun `Fetches data when fetchData returns emptyList `() {
        every { repository.fetchData() } returns emptyList()
        app.showData()
        val captureSlot = slot<List<UIDataModel>>()
        verify(exactly = 1) { view.onResult(capture(captureSlot)) }
        captureSlot.captured.let {
            assert(it.isEmpty())
            assertNotNull(it)
        }
    }

    @Test
    fun `Log works`() {
        val spiedApp = spyk(app)
        every { repository.fetchData() } returns emptyList()
        spiedApp.showData()
        verify { spiedApp.log(any(), any()) }
    }
}