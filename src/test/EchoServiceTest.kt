package echo.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EchoServiceTest {
    @Test
    fun `echo should return the same message`() {
        val echoService = EchoService()
        val message = "Hello, World!"
        val result = echoService.echo("x")

        assertEquals(message, result)
    }
}
