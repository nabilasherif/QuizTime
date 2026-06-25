package com.example.presentation.config

import com.example.domain.repository.IssueReportRepository
import com.example.domain.repository.QuizQuestionRepository
import com.example.domain.repository.QuizTopicRepository
import com.example.presentation.routes.issueReportRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import com.example.presentation.routes.root
import com.example.presentation.routes.quizQuestionRoutes
import com.example.presentation.routes.quizTopicRoutes
import com.example.presentation.routes.quiz_topic.*
import org.koin.ktor.ext.inject


fun Application.configureRouting() {

    val quizQuestionRepository: QuizQuestionRepository by inject()
    val quizTopicRepository: QuizTopicRepository by inject()
    val issueReportRepository: IssueReportRepository by inject()

    routing {
        root()

        quizQuestionRoutes(quizQuestionRepository)
        quizTopicRoutes(quizTopicRepository)
        issueReportRoutes(issueReportRepository)

        staticResources(
            remotePath = "/images",
            basePackage = "images"
        )

    }
}