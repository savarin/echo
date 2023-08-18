package com.echo.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EchoServiceTest {
    @Test
    fun `echo should return the same message`() {
        // Initialize EchoService
        val echoService = EchoService()

        // Define the expected message and invoke the echo method with a different input
        val message = "Hello, World!"
        val result = echoService.echo(message)

        // Verify that the result is equal to the expected message
        assertEquals(message, result)
    }
}
