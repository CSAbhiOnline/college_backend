package com.example.SERVICES

import com.example.DATA_CLASSES.College
import com.example.Databasefactory.dbQuery
import com.example.TABLES.CollegeTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.lowerCase
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class CollegeService {

    private fun toCollege(row: ResultRow): College = College(
        id = row[CollegeTable.id],
        name = row[CollegeTable.name],
        slug = row[CollegeTable.slug],
        ownership = row[CollegeTable.ownership],
        established = row[CollegeTable.established],
        state = row[CollegeTable.state],
        city = row[CollegeTable.city],
        category = row[CollegeTable.category],
        courses = row[CollegeTable.courses].split(","),
        logo = row[CollegeTable.logo],
        overview = row[CollegeTable.overview],
        coursesAndFees = row[CollegeTable.coursesAndFees].split(","),
        average_package = row[CollegeTable.average_package],
        graduation_percentage = row[CollegeTable.graduation_percentage],
        facilities = row[CollegeTable.facilities].split(","),
        amenities = row[CollegeTable.amenities]?.split(","),
        cutoff = row[CollegeTable.cutoff],
        total_faculty = row[CollegeTable.total_faculty],
        student_ratio = row[CollegeTable.student_ratio]
    )

    suspend fun getAllColleges(): List<College> = dbQuery {
        CollegeTable.selectAll().map(::toCollege)
    }

    suspend fun getCollegeById(id: Long): College? = dbQuery {
        CollegeTable.selectAll().where { CollegeTable.id eq id }.map(::toCollege).singleOrNull()
    }

    suspend fun getCollegesByName(name: String): List<College> = dbQuery {
        CollegeTable.selectAll().where { CollegeTable.name.lowerCase() like "%${name.lowercase()}%" }.map(::toCollege)
    }

    suspend fun getCollegesByCategory(category: String): List<College> = dbQuery {
        CollegeTable.selectAll().where { CollegeTable.category eq category }.map(::toCollege)
    }

    suspend fun getCollegesByState(state: String): List<College> = dbQuery {
        CollegeTable.selectAll().where { CollegeTable.state.lowerCase() like "%${state.lowercase()}%" }.map(::toCollege)
    }

    suspend fun getCollegesByCity(city: String): List<College> = dbQuery {
        CollegeTable.selectAll().where { CollegeTable.city.lowerCase() like "%${city.lowercase()}%" }.map(::toCollege)
    }

    suspend fun deleteCollege(id: Long): Boolean = dbQuery {
        CollegeTable.deleteWhere { CollegeTable.id eq id } > 0
    }

    suspend fun addCollege(college: College) = dbQuery {
        CollegeTable.insert {
            it[name] = college.name
            it[slug] = college.slug
            it[ownership] = college.ownership
            it[established] = college.established
            it[state] = college.state
            it[city] = college.city
            it[category] = college.category
            it[courses] = college.courses.joinToString(",")
            it[logo] = college.logo
            it[overview] = college.overview
            it[coursesAndFees] = college.coursesAndFees.joinToString(",")
            it[average_package] = college.average_package
            it[graduation_percentage] = college.graduation_percentage
            it[facilities] = college.facilities.joinToString(",")
            it[amenities] = college.amenities?.joinToString(",")
            it[cutoff] = college.cutoff
            it[total_faculty] = college.total_faculty
            it[student_ratio] = college.student_ratio
        }
    }
}
