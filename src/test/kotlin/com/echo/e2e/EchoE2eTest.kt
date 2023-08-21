package com.echo.e2e

import com.echo.client.EchoClient
import com.echo.service.EchoService
import com.echo.store.EchoLogStore
import com.echo.rpc.EchoServiceRpc
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Clock

class EchoServiceTest {
    // Define server name, service, server builder, channel, and client
    private val serverName = "in-process-test-server"
    private val echoService = EchoService(Clock.systemUTC(), EchoLogStore)
    private val serverBuilder = InProcessServerBuilder.forName(serverName)
        .directExecutor()
        .addService(EchoServiceRpc(echoService))
        .build()

    private val channel = InProcessChannelBuilder.forName(serverName)
        .directExecutor()
        .build()

    private val echoClient = EchoClient(channel)

    @BeforeEach
    fun before() {
        // Start the server before each test
        serverBuilder.start()
    }

    @Test
    fun echo() = runBlocking {
        // Arrange: Get the size of all logs before the test
        val preSize = EchoLogStore.getAll().size

        // Act: Call the client's echo method
        val result = echoClient.echo("foo")

        // Assert: Verify the result and the change in log size
        assertEquals("3:foo", result)

        val postSize = EchoLogStore.getAll().size
        assertEquals(preSize + 1, postSize)
    }
}
