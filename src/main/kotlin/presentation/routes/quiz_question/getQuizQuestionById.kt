package com.example.presentation.routes.quiz_question

import com.example.domain.repository.QuizQuestionRepository
import com.example.presentation.config.quizQuestions
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.http.HttpStatusCode

fun Route.getQuizQuestionById(quizQuestionRepository: QuizQuestionRepository) {
    get(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        if (id.isNullOrBlank()) {
            call.respond(HttpStatusCode.BadRequest, message= "Question id is required")
            return@get
        }

        val question = quizQuestionRepository.getQuestionById(id)

        if (question != null) {
            call.respond(HttpStatusCode.OK,question)
        } else {
            call.respond(HttpStatusCode.NotFound, "Question not found")
        }
    }
}