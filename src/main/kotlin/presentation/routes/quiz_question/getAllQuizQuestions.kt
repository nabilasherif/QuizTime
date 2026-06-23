package com.example.presentation.routes.quiz_question

import com.example.domain.repository.QuizQuestionRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Route.getAllQuizQuestions(quizQuestionRepository: QuizQuestionRepository) {

    get(path = "/quiz/questions") {
        val topicCode = call.queryParameters["topicCode"]?.toIntOrNull()
        val limit = call.queryParameters["limit"]?.toIntOrNull()

        val filteredQuestions = quizQuestionRepository.getAllQuestions(topicCode, limit)

        if (filteredQuestions.isEmpty()) {
            call.respond(HttpStatusCode.NotFound, "No questions found")
        } else {
            call.respond(HttpStatusCode.OK, filteredQuestions)
        }
    }
}