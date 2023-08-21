package com.echo.rpc

import com.echo.proto.EchoRequest
import com.echo.proto.EchoResponse
import com.echo.proto.EchoServiceGrpcKt
import com.echo.service.EchoService
import com.echo.store.EchoLogStore
import io.grpc.inprocess.InProcessServerBuilder
import io.grpc.inprocess.InProcessChannelBuilder
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Clock

class EchoServiceRpcTest {

    private lateinit var serverName: String
    private lateinit var server: io.grpc.Server
    private lateinit var channel: io.grpc.ManagedChannel

    @BeforeEach
    fun setup() {
        // Generate unique name for in-process server and create server and channel
        serverName = InProcessServerBuilder.generateName()
        server = InProcessServerBuilder.forName(serverName).directExecutor()
            .addService(EchoServiceRpc(EchoService(Clock.systemUTC(), EchoLogStore))).build().start()
        channel = InProcessChannelBuilder.forName(serverName).directExecutor().build() as io.grpc.ManagedChannel
    }

    @AfterEach
    fun cleanup() {
        // Shutdown channel and server after test execution
        channel.shutdown()
        server.shutdownNow()
    }

    @Test
    fun `test echo rpc`() = runBlocking {
        // Arrange: Define request message
        val message = "Hello, World!"
        val request = EchoRequest.newBuilder().setMessage(message).build()

        // Act: Invoke RPC call and get the response
        val response = EchoServiceGrpcKt.EchoServiceCoroutineStub(channel).echo(request)

        // Assert: Verify that the response is as expected
        assertEquals(EchoResponse.newBuilder().setMessage("13:$message").build(), response)
    }
}
