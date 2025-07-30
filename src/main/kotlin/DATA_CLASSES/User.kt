package com.example.DATA_CLASSES

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val name: String,
    val phoneNumber: Int
)
