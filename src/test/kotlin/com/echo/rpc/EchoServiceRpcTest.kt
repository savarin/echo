package com.echo.rpc

import com.echo.proto.EchoRequest
import com.echo.proto.EchoResponse
import com.echo.proto.EchoServiceGrpcKt
import com.echo.service.EchoService
import io.grpc.inprocess.InProcessServerBuilder
import io.grpc.inprocess.InProcessChannelBuilder
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EchoServiceRpcTest {

    private lateinit var serverName: String
    private lateinit var server: io.grpc.Server
    private lateinit var channel: io.grpc.ManagedChannel

    @BeforeEach
    fun setup() {
        // Generate unique name for in-process server
        serverName = InProcessServerBuilder.generateName()

        // Create and start an in-process server with EchoServiceRpc implementation
        server = InProcessServerBuilder.forName(serverName).directExecutor()
            .addService(EchoServiceRpc(EchoService())).build().start()

        // Create in-process channel for communication with the server
        channel = InProcessChannelBuilder.forName(serverName).directExecutor().build() as io.grpc.ManagedChannel
    }

    @AfterEach
    fun cleanup() {
        channel.shutdown()
        server.shutdownNow()
    }

    @Test
    fun `test echo rpc`() = runBlocking {
        // Define request message
        val requestMessage = "Hello, World!"
        val request = EchoRequest.newBuilder().setMessage(requestMessage).build()

        // Invoke RPC call and get the response
        val response = EchoServiceGrpcKt.EchoServiceCoroutineStub(channel).echo(request)

        // Verify that the response is as expected
        assertEquals(EchoResponse.newBuilder().setMessage("13:Hello, World!").build(), response)
    }
}
