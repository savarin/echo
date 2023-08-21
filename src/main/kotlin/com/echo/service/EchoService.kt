package com.echo.service

import com.echo.model.EchoLogEntity
import com.echo.store.EchoLogStore
import java.time.Clock

/**
 * Service implementation that handles the echoing of messages. It transforms the input by
 * prefixing the length of the original message, followed by the message itself.
 */
class EchoService constructor(
    private val clock: Clock,
    private val store: EchoLogStore,
) {

    /**
     * Echoes the input message with a specific format. It calculates the length of the input
     * message and prefixes it to the original message.
     *
     * @param message The input message to be echoed.
     * @return The transformed message containing the length followed by the original message.
     */
    fun echo(message: String): String {
        store.insert(EchoLogEntity.create(message = message, now = clock.instant()))
        return "${message.length}:$message"
    }
}
