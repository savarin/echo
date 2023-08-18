import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EchoServiceTest {
    @Test
    fun `echo should return the same message`() {
        assertEquals("Hello, World!", EchoService().echo("Hello, World!"))
    }
}
