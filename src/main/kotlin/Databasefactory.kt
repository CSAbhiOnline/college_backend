package com.example

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import com.example.TABLES.CollegeTable
import com.example.TABLES.UserTable

object Databasefactory {

    fun init() {
        Database.connect(createHikariDataSource())
        transaction {
            SchemaUtils.create(
                CollegeTable,
                UserTable
            )
        }
    }

    private fun createHikariDataSource(): HikariDataSource {
        val config = HikariConfig()
        val dotenv= dotenv()
        config.jdbcUrl = dotenv["JDBC_UR"]
        config.driverClassName = dotenv["JDBC_DRIVER"]

        config.username =  dotenv["DB_USER"]
        config.password =  dotenv["DB_PASSWOR"]
        config.maximumPoolSize = 7
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction { block() }

}