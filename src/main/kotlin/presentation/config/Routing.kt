package com.example.presentation.config

import com.example.data.database.DatabaseFactory
import com.example.data.repository.QuizQuestionRepositoryImpl
import com.example.data.repository.QuizTopicRepositoryImpl
import com.example.domain.model.QuizQuestion
import com.example.domain.repository.QuizQuestionRepository
import com.example.domain.repository.QuizTopicRepository
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import com.example.presentation.routes.root
import com.example.presentation.routes.quiz_question.*
import com.example.presentation.routes.quiz_topic.*


fun Application.configureRouting() {

    val mongoDatabase= DatabaseFactory.create()
    val quizQuestionRepository: QuizQuestionRepository = QuizQuestionRepositoryImpl(mongoDatabase)
    val quizTopicRepository: QuizTopicRepository = QuizTopicRepositoryImpl(mongoDatabase)
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

        staticResources(
            remotePath = "/images",
            basePackage = "images"
        )

    }
}

val quizQuestions = mutableListOf<QuizQuestion>()