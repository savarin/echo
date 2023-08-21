package com.echo.service

import com.echo.store.EchoLogStore
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Clock

class EchoServiceTest {
    @BeforeEach
    fun setUp() {
        EchoLogStore.setUp("jdbc:sqlite:echo.db")
    }

    @AfterEach
    fun tearDown() {
        EchoLogStore.tearDown()
    }

    @Test
    fun `echo should return the same message`() {
        // Arrange: Initialize EchoService and define the expected message
        val echoService = EchoService(Clock.systemUTC(), EchoLogStore)
        val message = "Hello, World!"

        // Act: Invoke the echo method with the input message
        val result = echoService.echo(message)

        // Assert: Verify that the result is equal to the expected message
        assertEquals("13:$message", result)
    }
}
