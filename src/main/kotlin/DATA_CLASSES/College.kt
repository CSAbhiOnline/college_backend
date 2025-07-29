package com.example.DATA_CLASSES

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class College(
    val id: Long,
    val name: String,
    val slug: String,
    val ownership: String,
    val established: String,
    val state: String,
    val city: String,
    val category: String,
    val courses: List<String>,
    val logo: String,
    val overview: String,
    @SerialName("coursesAndFees")
    val coursesAndFees: List<CourseAndFees>,
    val placements: Placements?,
    val amenities: List<String>?,
    val cutoff: Cutoff?,
    val faculty: Faculty?
)

@Serializable
data class CourseAndFees(
    val name: String,
    val duration: String,
    @SerialName("totalFees")
    val totalFees: String,
    val seats: Int,
    val level: String
)

@Serializable
data class Placements(
    @SerialName("averagePackage")
    val averagePackage: String,
    @SerialName("graduationPercentage")
    val graduationPercentage: GraduationPercentage
)

@Serializable
data class GraduationPercentage(
    val ug: List<Double>,
    val pg: List<Double>,
    val years: List<Int>
)

@Serializable
data class Cutoff(
    val mbbs: String?,
    val md: String?
)

@Serializable
data class Faculty(
    val total: Int,
    @SerialName("studentRatio")
    val studentRatio: String
)

@Serializable
data class CollegeResponse(
    val colleges: List<College>
)
