package com.example.data.database.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class IssueReportEntity(
    @SerialName("_id")
    val _id: String = ObjectId().toString(),
    val questionId: String,
    val issueType: String,
    val additionalComment: String?,
    val userEmail: String?,
    val timeStamp: String
)