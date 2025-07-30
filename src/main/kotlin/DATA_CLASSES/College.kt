package com.example.DATA_CLASSES

import jdk.jfr.internal.Cutoff
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
    val coursesAndFees: List<String>,
    val average_package: String,
    val graduation_percentage: String,
    val facilities: List<String>,
    val amenities: List<String>?,
    val cutoff: String?,
    val total_faculty: Int,
    val student_ratio: String
)
