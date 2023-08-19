package com.echo.rpc

import com.echo.proto.EchoRequest
import com.echo.proto.EchoResponse
import com.echo.proto.EchoServiceGrpcKt.EchoServiceCoroutineImplBase
import com.echo.service.EchoService

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
