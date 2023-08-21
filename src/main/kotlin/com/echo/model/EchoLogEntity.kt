package com.echo.model

import com.echo.db.jooq.tables.records.EchoLogRecord
import java.time.LocalDateTime
import java.time.Instant
import java.util.UUID

/**
 * Data class representing an echo log entity with ID, message, and created-at timestamp.
 *
 * @property id The unique identifier for the log entry.
 * @property message The message contained in the log entry.
 * @property createdAt The timestamp of when the log entry was created.
 */
data class EchoLogEntity(
    val id: String,
    val message: String,
    val createdAt: Instant,
) {

    /**
     * Converts the EchoLogEntity object to an EchoLogRecord object, mapping the properties accordingly.
     *
     * @return The converted EchoLogRecord object.
     */
    fun toRecord(): EchoLogRecord = EchoLogRecord().also { record ->
        record.id = id
        record.message = message
        record.createdAt = LocalDateTime.ofInstant(createdAt, java.time.ZoneOffset.UTC)
    }

    companion object {
        /**
         * Creates an EchoLogEntity object with the given ID, message, and timestamp. If no ID is provided, a new UUID is generated.
         *
         * @param id The unique identifier for the log entry. Defaults to a newly generated UUID.
         * @param message The message to be contained in the log entry.
         * @param now The timestamp for when the log entry is created.
         * @return The created EchoLogEntity object.
         */
        fun create(
            id: String = UUID.randomUUID().toString(),
            message: String,
            now: Instant
        ) = EchoLogEntity(
            id = id,
            message = message,
            createdAt = now,
        )

        /**
         * Converts an EchoLogRecord object to an EchoLogEntity object, mapping the properties accordingly.
         *
         * @param record The EchoLogRecord object to be converted.
         * @return The converted EchoLogEntity object.
         */
        fun fromRecord(record: EchoLogRecord): EchoLogEntity = EchoLogEntity(
            id = record.id,
            message = record.message,
            createdAt = record.createdAt.toInstant(java.time.ZoneOffset.UTC)
        )
    }
}
