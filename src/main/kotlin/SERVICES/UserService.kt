package com.example.SERVICES

import com.example.DATA_CLASSES.User
import com.example.Databasefactory.dbQuery
import com.example.TABLES.UserTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert

class UserService {

    private fun toUser(row: ResultRow): User = User(
        id = row[UserTable.id],
        name = row[UserTable.name],
        phoneNumber = row[UserTable.phoneNumber]
    )

    suspend fun createUser(user: User): User? = dbQuery {
        val insertStatement = UserTable.insert {
            it[name] = user.name
            it[phoneNumber] = user.phoneNumber
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::toUser)
    }
}