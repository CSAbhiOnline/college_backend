package com.example

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.Database

object Databasefactory {

    fun init() {
        Database.connect(createHikariDataSource())
    }

    private fun createHikariDataSource(): HikariDataSource {
        val config = HikariConfig()
        val dotenv= dotenv()
        config.driverClassName = dotenv["JDBC_DRIVER"]
            config.jdbcUrl = dotenv["JDBC_URL"]
        config.username =  dotenv["DB_USER"]
        config.password =  dotenv["DB_PASSWORD"]
        config.maximumPoolSize = 7
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

}