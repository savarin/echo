import echo.proto.EchoRequest
import echo.proto.EchoResponse
import echo.proto.EchoServiceGrpcKt
import echo.service.EchoService
import echo.rpc.EchoServiceRpc
import io.grpc.inprocess.InProcessServerBuilder
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.testing.GrpcCleanupRule
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class EchoServiceRpcTest {

    // Rule to clean up gRPC resources after test execution
    @get:Rule
    val grpcCleanup = GrpcCleanupRule()

    @Test
    fun `test echo rpc`() {
        // Generate unique name for in-process server
        val serverName = InProcessServerBuilder.generateName()

        // Create and start an in-process server with EchoServiceRpc implementation
        val server = InProcessServerBuilder.forName(serverName).directExecutor()
            .addService(EchoServiceRpc(EchoService())).build().start()
        grpcCleanup.register(server)

        // Create in-process channel for communication with the server
        val channel = InProcessChannelBuilder.forName(serverName).directExecutor().build()
        grpcCleanup.register(channel)

        // Define request message
        val requestMessage = "Hello, World!"
        val request = EchoRequest.newBuilder().setMessage(requestMessage).build()

        // Invoke RPC call and get the response
        val response = EchoServiceGrpcKt.EchoServiceCoroutineStub(channel).echo(request)

        // Verify that the response is as expected
        assertEquals(EchoResponse.newBuilder().setMessage(requestMessage).build(), response)
    }
}
