package com.example.presentation.config

import com.example.data.database.DatabaseFactory
import com.example.data.repository.QuizQuestionRepositoryImpl
import com.example.data.repository.QuizTopicRepositoryImpl
import com.example.data.repository.IssueReportRepositoryImpl
import com.example.domain.model.QuizQuestion
import com.example.domain.repository.IssueReportRepository
import com.example.domain.repository.QuizQuestionRepository
import com.example.domain.repository.QuizTopicRepository
import com.example.presentation.routes.issue_report.issueReportRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import com.example.presentation.routes.root
import com.example.presentation.routes.quiz_question.*
import com.example.presentation.routes.quiz_topic.*
import org.koin.ktor.ext.inject


fun Application.configureRouting() {

    val quizQuestionRepository: QuizQuestionRepository by inject()
    val quizTopicRepository: QuizTopicRepository by inject()
    val issueReportRepository: IssueReportRepository by inject()

    routing {
        root()

        // Quiz Questions
        getAllQuizQuestions(quizQuestionRepository)
        upsertQuizQuestion(quizQuestionRepository)
        deleteQuizQuestionById(quizQuestionRepository)
        getQuizQuestionById(quizQuestionRepository)

        //Quiz Topics
        getAllQuizTopics(quizTopicRepository)
        upsertQuizTopic(quizTopicRepository)
        deleteQuizTopicById(quizTopicRepository)
        getQuizTopicById(quizTopicRepository)

        issueReportRoutes(issueReportRepository)

        staticResources(
            remotePath = "/images",
            basePackage = "images"
        )

    }
}

val quizQuestions = mutableListOf<QuizQuestion>()