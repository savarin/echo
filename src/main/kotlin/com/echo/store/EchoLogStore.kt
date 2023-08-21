package com.echo.store

import com.echo.db.jooq.Tables.ECHO_LOG
import com.echo.db.jooq.tables.records.EchoLogRecord
import com.echo.model.EchoLogEntity
import org.flywaydb.core.Flyway
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.DriverManager
import java.time.Instant
import java.util.UUID

object EchoLogStore {
    private const val URL = "jdbc:sqlite:echo.db"

    // Private method to establish a connection with the SQLite database.
    private fun connect(): DSLContext {
        return DSL.using(DriverManager.getConnection(URL), SQLDialect.SQLITE)
    }

    // Function to execute migration scripts using Flyway.
    fun migrate(url: String = URL) {
        val flyway = Flyway.configure()
            .dataSource(url, null, null)
            .locations("classpath:db/migration")
            .load()

        flyway.migrate()
    }

    // Function to insert a log entry represented by EchoLogEntity into the database.
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

    // Private function to fetch all log records from the database.
    private fun fetchRecords(): List<EchoLogRecord> {
        val ctx = connect()
        return ctx.select()
            .from(ECHO_LOG)
            .fetchInto(EchoLogRecord::class.java)
    }

    // Function to retrieve all log entities from the database.
    fun getAll(): List<EchoLogEntity> {
        return fetchRecords().map { record -> EchoLogEntity.fromRecord(record) }
    }

    // Function to print all log records to the console.
    fun printLogs() {
        fetchRecords().forEach { record ->
            println("ID: ${record.id}, Message: ${record.message}, Created At: ${record.createdAt}")
        }
    }
}

// Main function handling migration or insertion and printing of log records.
fun main(args: Array<String>) {
    when {
        args.contains("--migrate") -> EchoLogStore.migrate()
        else -> {
            EchoLogStore.insert(EchoLogEntity(UUID.randomUUID().toString(), "Hello, World!", Instant.now()))
            EchoLogStore.printLogs()
        }
    }
}
