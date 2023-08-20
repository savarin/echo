package com.echo.store

import org.flywaydb.core.Flyway
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.DriverManager

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

    fun insertLog(id: String, message: String, createdAt: String) {
        val ctx = connect()
        ctx.insertInto(DSL.table("echo_log"))
            .columns(DSL.field("id"), DSL.field("message"), DSL.field("created_at"))
            .values(id, message, createdAt)
            .execute()
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
            EchoLogStore.insertLog("id1", "Hello, World!", "2023-08-18 10:30:00")
            EchoLogStore.printLogs()
        }
    }
}
