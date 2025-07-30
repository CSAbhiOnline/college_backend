package com.example.TABLES

import org.jetbrains.exposed.sql.Table

object UserTable : Table("UserTable") {
    val id = long("id").autoIncrement()
    val name = varchar("name", 256)
    val phoneNumber = integer("phoneNumber")

    override val primaryKey = PrimaryKey(id)
}
