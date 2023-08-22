package com.echo.proxy

import com.echo.proto.EchoRequest
import com.echo.proto.EchoServiceGrpc
import io.grpc.ManagedChannelBuilder
import spark.Spark.post
import spark.Spark.port

fun main() {
    // Set the HTTP server port to listen on
    port(8081)

    // Create a gRPC channel and stub for communication with the Echo service
    val channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build()
    val stub = EchoServiceGrpc.newBlockingStub(channel)

    // Define a POST endpoint for the /echo route that handles the echo requests
    post("/echo") { req, _ ->
        // Retrieve the message from the request body
        val message = req.body()

        // Build the EchoRequest object using the retrieved message
        val request = EchoRequest.newBuilder().setMessage(message).build()

        // Invoke the echo method on the stub and get the response
        val response = stub.echo(request)

        // Return the response message as the HTTP response
        response.message
    }
}
