package com.example.SERVICES

import com.example.DATA_CLASSES.College
import com.example.TABLES.*
import com.example.Databasefactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.InsertStatement

class CollegeService {

    suspend fun createCollege(college: College): College {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = Colleges.insert {
                it[name] = college.name
                it[slug] = college.slug
                it[ownership] = college.ownership
                it[established] = college.established
                it[state] = college.state
                it[city] = college.city
                it[category] = college.category
                it[logo] = college.logo
                it[overview] = college.overview
            }

            val collegeId = statement!!.resultedValues!!.first()[Colleges.id]

            college.courses.forEach { courseName ->
                CollegeCourses.insert {
                    it[CollegeCourses.collegeId] = collegeId
                    it[CollegeCourses.courseName] = courseName
                }
            }

            college.coursesAndFees.forEach { courseAndFees ->
                CoursesAndFees.insert {
                    it[CoursesAndFees.collegeId] = collegeId
                    it[name] = courseAndFees.name
                    it[duration] = courseAndFees.duration
                    it[totalFees] = courseAndFees.totalFees
                    it[seats] = courseAndFees.seats
                    it[level] = courseAndFees.level
                }
            }

            college.placements?.let { placements ->
                Placements.insert {
                    it[Placements.collegeId] = collegeId
                    it[averagePackage] = placements.averagePackage
                }
                GraduationPercentages.insert {
                    it[placementId] = collegeId
                    it[ug] = placements.graduationPercentage.ug.joinToString(",")
                    it[pg] = placements.graduationPercentage.pg.joinToString(",")
                    it[years] = placements.graduationPercentage.years.joinToString(",")
                }
            }

            college.amenities?.forEach { amenityName ->
                Amenities.insert {
                    it[collegeId] = collegeId
                    it[name] = amenityName
                }
            }

            college.cutoff?.let { cutoff ->
                Cutoffs.insert {
                    it[collegeId] = collegeId
                    it[mbbs] = cutoff.mbbs
                    it[md] = cutoff.md
                }
            }

            college.faculty?.let { faculty ->
                Faculties.insert {
                    it[collegeId] = collegeId
                    it[total] = faculty.total
                    it[studentRatio] = faculty.studentRatio
                }
            }
        }
        return rowToCollege(statement!!.resultedValues!!.first())!!
    }

    private fun rowToCollege(row: ResultRow): College? {
        if (row == null) {
            return null
        }
        return College(
            id = row[Colleges.id],
            name = row[Colleges.name],
            slug = row[Colleges.slug],
            ownership = row[Colleges.ownership],
            established = row[Colleges.established],
            state = row[Colleges.state],
            city = row[Colleges.city],
            category = row[Colleges.category],
            logo = row[Colleges.logo],
            overview = row[Colleges.overview],
            courses = emptyList(),
            coursesAndFees = emptyList(),
            placements = null,
            amenities = null,
            cutoff = null,
            faculty = null
        )
    }
}
