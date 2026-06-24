package com.example.presentation.routes.quiz_question

import com.example.domain.repository.QuizQuestionRepository
import io.ktor.server.routing.Route
import io.ktor.server.response.*
import io.ktor.server.resources.*
import io.ktor.http.HttpStatusCode

fun Route.getQuizQuestionById(quizQuestionRepository: QuizQuestionRepository) {
    get<QuestionRoutesPath.ById> { path ->
        runCatching {
            quizQuestionRepository.getQuestionById(path.questionId)
        }.onSuccess { question ->
            if (question != null) {
                call.respond(HttpStatusCode.OK, question)
            } else {
                call.respond(HttpStatusCode.NotFound, "Question not found")
            }
        }.onFailure {
            call.respond(HttpStatusCode.InternalServerError, "Failed to retrieve question")
        }
    }
}