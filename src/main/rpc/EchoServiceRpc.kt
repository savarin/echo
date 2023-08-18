package echo.rpc

import echo.proto.EchoRequest
import echo.proto.EchoResponse
import echo.proto.EchoServiceGrpcKt.EchoServiceCoroutineImplBase
import echo.service.EchoService

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
