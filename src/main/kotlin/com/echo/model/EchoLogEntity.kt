package com.echo.model

import com.echo.db.jooq.tables.records.EchoLogRecord
import java.time.LocalDateTime
import java.time.Instant
import java.util.UUID

data class EchoLogEntity(
    val id: String,
    val message: String,
    val createdAt: Instant,
) {

    fun toRecord(): EchoLogRecord = EchoLogRecord().also { record ->
        record.id = id
        record.message = message
        record.createdAt = LocalDateTime.ofInstant(createdAt, java.time.ZoneOffset.UTC)
    }

    companion object {
        fun create(
            id: String = UUID.randomUUID().toString(),
            message: String,
            now: Instant
        ) = EchoLogEntity(
            id = id,
            message = message,
            createdAt = now,
        )

        fun fromRecord(record: EchoLogRecord): EchoLogEntity = EchoLogEntity(
            id = record.id,
            message = record.message,
            createdAt = record.createdAt.toInstant(java.time.ZoneOffset.UTC)
        )
    }
}
