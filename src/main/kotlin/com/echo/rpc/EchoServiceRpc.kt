package com.echo.rpc

import com.echo.proto.EchoRequest
import com.echo.proto.EchoResponse
import com.echo.proto.EchoServiceGrpcKt.EchoServiceCoroutineImplBase
import com.echo.service.EchoService
import io.grpc.ServerBuilder

/**
 * RPC service implementation that handles remote procedure calls related to the Echo service. It
 * leverages the human-friendly interface defined in EchoService.
 *
 * @property echoService The underlying EchoService that implements the core logic.
 */
class EchoServiceRpc constructor(
    private val echoService: EchoService,
) : EchoServiceCoroutineImplBase() {

    /**
     * Implementation of the Echo RPC method. It delegates the actual logic to the EchoService, thus
     * maintaining a separation between the RPC layer and the service logic.
     *
     * @param request The EchoRequest object containing the message to be echoed.
     * @return The EchoResponse object containing the echoed message.
     */
    override suspend fun echo(request: EchoRequest): EchoResponse {
        // Retrieve the message from the request
        val message = echoService.echo(request.message)

        // Build and return the response by encapsulating the echoed message
        return EchoResponse
            .newBuilder()
            .setMessage(message)
            .build()
    }
}

class EchoServer {
    // Server instance configured to run on port 8080 and to handle RPC requests using the
    // EchoServiceRpc class, which delegates the requests to the EchoService implementation
    private val server = ServerBuilder.forPort(8080)
        .addService(EchoServiceRpc(EchoService()))
        .build()

    /**
     * Starts the gRPC server, enabling it to accept incoming connections and RPC requests.
     * It prints a message to the console indicating that the server is listening on port 8080,
     * and then it blocks until the server is terminated.
     */
    fun start() {
        server.start()
        println("Server started, listening on 8080")
        server.awaitTermination()
    }
}

fun main() {
    EchoServer().start()
}
