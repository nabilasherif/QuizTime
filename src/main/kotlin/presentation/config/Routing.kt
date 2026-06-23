package com.example.presentation.config

import com.example.data.repository.QuizQuestionRepositoryImpl
import com.example.domain.model.QuizQuestion
import com.example.domain.repository.QuizQuestionRepository
import io.ktor.server.application.*
import io.ktor.server.routing.*
import com.example.presentation.routes.root
import com.example.presentation.routes.quiz_question.*

fun Application.configureRouting() {
    val quizQuestionRepository: QuizQuestionRepository = QuizQuestionRepositoryImpl()
    routing {
        root()

        getAllQuizQuestions(quizQuestionRepository)
        upsertQuizQuestion(quizQuestionRepository)
        deleteQuizQuestionById(quizQuestionRepository)
        getQuizQuestionById(quizQuestionRepository)
    }
}

val quizQuestions = mutableListOf<QuizQuestion>()