package com.echo.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EchoServiceTest {
    @Test
    fun `echo should return the same message`() {
        // Arrange: Initialize EchoService and define the expected message
        val echoService = EchoService()
        val message = "Hello, World!"

        // Act: Invoke the echo method with the input message
        val result = echoService.echo(message)

        // Assert: Verify that the result is equal to the expected message
        assertEquals("13:$message", result)
    }
}
