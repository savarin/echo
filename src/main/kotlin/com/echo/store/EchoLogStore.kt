package com.echo.store

import com.echo.db.jooq.Tables.ECHO_LOG
import com.echo.model.EchoLogEntity
import org.flywaydb.core.Flyway
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.DriverManager
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object EchoLogStore {
    private const val URL = "jdbc:sqlite:echo.db"

    private fun connect(): DSLContext {
        return DSL.using(DriverManager.getConnection(URL), SQLDialect.SQLITE)
    }

    fun migrate() {
        val flyway = Flyway.configure()
            .dataSource(URL, null, null)
            .locations("classpath:db/migration")
            .load()

        flyway.migrate()
    }

    fun insert(log: EchoLogEntity) {
        val ctx = connect()
        ctx.transaction { configuration ->
            val transactionCtx = DSL.using(configuration)
            transactionCtx
                .insertInto(ECHO_LOG)
                .set(log.toRecord())
                .execute()
        }
    }

    fun printLogs() {
        val ctx = connect()
        ctx.select()
            .from("echo_log")
            .fetch()
            .forEach { record ->
                println("ID: ${record["id"]}, Message: ${record["message"]}, Created At: ${record["created_at"]}")
            }
    }
}

fun main(args: Array<String>) {
    when {
        args.contains("--migrate") -> EchoLogStore.migrate()
        else -> {
            val timestampString = "2023-08-18 10:30:00"
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val localDateTime = LocalDateTime.parse(timestampString, formatter)
            val instant = localDateTime.toInstant(ZoneOffset.UTC)

            EchoLogStore.insert(EchoLogEntity("id1", "Hello, World!", instant))
            EchoLogStore.printLogs()
        }
    }
}
