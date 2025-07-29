package com.example.TABLES

import org.jetbrains.exposed.sql.Table

object Colleges : Table() {
    val id = long("id").autoIncrement()
    val name = varchar("name", 255)
    val slug = varchar("slug", 255)
    val ownership = varchar("ownership", 255)
    val established = varchar("established", 255)
    val state = varchar("state", 255)
    val city = varchar("city", 255)
    val category = varchar("category", 255)
    val logo = varchar("logo", 255)
    val overview = text("overview")

    override val primaryKey = PrimaryKey(id)
}

object CollegeCourses : Table() {
    val collegeId = long("college_id").references(Colleges.id)
    val courseName = varchar("course_name", 255)
}

object CoursesAndFees : Table() {
    val collegeId = long("college_id").references(Colleges.id)
    val name = varchar("name", 255)
    val duration = varchar("duration", 255)
    val totalFees = varchar("total_fees", 255)
    val seats = integer("seats")
    val level = varchar("level", 255)
}

object Placements : Table() {
    val collegeId = long("college_id").references(Colleges.id)
    val averagePackage = varchar("average_package", 255)
    override val primaryKey = PrimaryKey(collegeId)
}

object GraduationPercentages : Table() {
    val placementId = long("placement_id").references(Placements.collegeId)
    val ug = varchar("ug", 255) // Storing list as comma-separated string
    val pg = varchar("pg", 255) // Storing list as comma-separated string
    val years = varchar("years", 255) // Storing list as comma-separated string
    override val primaryKey = PrimaryKey(placementId)
}

object Amenities : Table() {
    val collegeId = long("college_id").references(Colleges.id)
    val name = varchar("name", 255)
}

object Cutoffs : Table() {
    val collegeId = long("college_id").references(Colleges.id)
    val mbbs = varchar("mbbs", 255).nullable()
    val md = varchar("md", 255).nullable()
    override val primaryKey = PrimaryKey(collegeId)
}

object Faculties : Table() {
    val collegeId = long("college_id").references(Colleges.id)
    val total = integer("total")
    val studentRatio = varchar("student_ratio", 255)
    override val primaryKey = PrimaryKey(collegeId)
}
