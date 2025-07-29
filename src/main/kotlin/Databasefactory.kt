package com.example

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import com.example.TABLES.*

object Databasefactory {

    fun init() {
        Database.connect(createHikariDataSource())
        transaction {
            SchemaUtils.create(
                Colleges,
                CollegeCourses,
                CoursesAndFees,
                Placements,
                GraduationPercentages,
                Amenities,
                Cutoffs,
                Faculties
            )
        }
    }

    private fun createHikariDataSource(): HikariDataSource {
        val config = HikariConfig()
        val dotenv= dotenv()
        config.jdbcUrl = dotenv["JDBC_URl"]
        config.driverClassName = dotenv["JDBC_DRIVER"]

        config.username =  dotenv["DB_USER"]
        config.password =  dotenv["DB_PASSWORD"]
        config.maximumPoolSize = 7
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

}