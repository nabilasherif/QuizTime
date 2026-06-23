package com.example.data.database.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import java.util.Collections.emptyList

@Serializable
data class QuizQuestionEntity(
    @SerialName("_id")
    val _id: String = ObjectId().toString(),
    val question: String = "",
    val correctAnswer: String = "",
    val incorrectAnswer: List<String> = emptyList(),
    val explanation: String = "",
    val topicCode: Int = 0
)