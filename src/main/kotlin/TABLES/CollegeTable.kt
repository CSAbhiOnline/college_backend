package com.example.TABLES

import org.jetbrains.exposed.sql.Table

object CollegeTable : Table("CollegeTable") {
    val id = long("id").autoIncrement()
    val name = varchar("name", 255)
    val slug = varchar("slug", 255)
    val ownership = varchar("ownership", 255)
    val established = varchar("established", 255)
    val state = varchar("state", 255)
    val city = varchar("city", 255)
    val category = varchar("category", 255)
    val courses = text("courses")
    val logo = varchar("logo", 255)
    val overview = text("overview")
    val coursesAndFees = text("courses_and_fees")
    val average_package = varchar("average_package", 255)
    val graduation_percentage = varchar("graduation_percentage", 255)
    val facilities = text("facilities")
    val amenities = text("amenities").nullable()
    val cutoff = varchar("cutoff", 255).nullable()
    val total_faculty = integer("total_faculty")
    val student_ratio = varchar("student_ratio", 255)

    override val primaryKey = PrimaryKey(id)
}