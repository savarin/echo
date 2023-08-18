package com.echo.rpc

import com.echo.proto.EchoRequest
import com.echo.proto.EchoResponse
import com.echo.proto.EchoServiceGrpcKt.EchoServiceCoroutineImplBase
import com.echo.service.EchoService

/**
 * RPC service implementation. The actual logic is implemented by the internal
 * service that has human friendly interface.
 */
class EchoServiceRpc constructor(
    private val echoService: EchoService,
) : EchoServiceCoroutineImplBase() {

    override suspend fun echo(request: EchoRequest): EchoResponse {
        val message = echoService.echo(request.message)
        return EchoResponse
            .newBuilder()
            .setMessage(message)
            .build()
    }
}
