package com.example.domain.model

import javax.lang.model.element.NestingKind

data class IssueReports(
    val id: String?=null,
    val questionId: String,
    val issueType: String,
    val additionalComment: String?,
    val userEmail: String?,
    val timeStamp: String
)