package com.echo.e2e

//import com.echo.client.EchoClient
//import com.echo.service.EchoService
//import com.echo.store.EchoLogStore
//import com.echo.rpc.EchoServiceRpc
//import io.grpc.inprocess.InProcessChannelBuilder
//import io.grpc.inprocess.InProcessServerBuilder
//import kotlinx.coroutines.runBlocking
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import java.time.Clock
//
//class EchoServiceTest {
//    private val serverName = "in-process-test-server"
//    private val echoService = EchoService(Clock.systemUTC(), EchoLogStore)
//    private val serverBuilder = InProcessServerBuilder.forName(serverName)
//        .directExecutor()
//        .addService(EchoServiceRpc(echoService))
//        .build()
//
//    private val channel = InProcessChannelBuilder.forName(serverName)
//        .directExecutor()
//        .build()
//
//    private val echoClient = EchoClient(channel)
//
//    @BeforeEach
//    fun before() {
//        serverBuilder.start()
//    }
//
//    @Test
//    fun echo() = runBlocking {
//        assertEquals(echoClient.echo("foo"), "3:foo")
//    }
//}
