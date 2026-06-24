package com.example.data.database.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class QuizTopicEntity(
    @SerialName("_id")
    val _id: String?= ObjectId().toString(),
    val name: String,
    val imageUrl: String,
    val code: Int
)
