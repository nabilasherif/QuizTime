package com.example.presentation.routes.quiz_question

import com.example.domain.repository.QuizQuestionRepository
import com.example.presentation.config.quizQuestions
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.http.HttpStatusCode

fun Route.deleteQuizQuestionById(quizQuestionRepository: QuizQuestionRepository) {
    delete("/quiz/questions/{questionId}") {
        val id= call.parameters["questionId"]
        if (id.isNullOrBlank()) {
            call.respond(HttpStatusCode.BadRequest, "Question id is required")
            return@delete
        }
        val isDeleted= quizQuestionRepository.deleteQuestionById(id)

        if (isDeleted) {
            call.respond(HttpStatusCode.NoContent)
        }else{
            call.respond(HttpStatusCode.NotFound)
        }
    }
}