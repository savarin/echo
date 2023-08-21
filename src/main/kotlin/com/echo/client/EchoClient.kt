package com.echo.client

import com.echo.proto.EchoRequest
import com.echo.proto.EchoServiceGrpcKt.EchoServiceCoroutineStub
import io.grpc.Channel
import io.grpc.ManagedChannelBuilder

/**
 * Client implementation for the Echo service that sends RPC requests to the server.
 * It constructs the RPC request and handles the response.
 *
 * @property rpc The EchoServiceCoroutineStub used to make RPC calls.
 */
class EchoClient (
    private val rpc: EchoServiceCoroutineStub,
) {
    /**
     * Secondary constructor that takes a gRPC Channel and creates an EchoServiceCoroutineStub from it.
     */
    constructor(channel: Channel) : this(EchoServiceCoroutineStub(channel))

    /**
     * Sends an echo request to the server with the provided message, and returns the echoed message.
     *
     * @param message The message to be echoed.
     * @return The echoed message received from the server.
     */
    suspend fun echo(message: String): String {
        // Build the EchoRequest object using the provided message
        val request = EchoRequest.newBuilder()
            .setMessage(message)
            .build()

        // Send the request to the server and return the echoed message
        return rpc.echo(request).message
    }
}

/**
 * Entry point for the client application. It creates a channel to the server, sends a message,
 * prints the server's response, and then shuts down the channel.
 */
suspend fun main(args: Array<String>) {
    // Create a channel to the server running on localhost at port 8080
    val channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build()

    // Create a client instance using the channel
    val client = EchoClient(EchoServiceCoroutineStub(channel))

    // Retrieve the message from the command-line arguments or use a default message
    val message = args.firstOrNull() ?: "default-message"

    // Send the message to the server and print the response
    val response = client.echo(message)
    println("Server responded with: $response")

    // Shutdown the channel
    channel.shutdown()
}
