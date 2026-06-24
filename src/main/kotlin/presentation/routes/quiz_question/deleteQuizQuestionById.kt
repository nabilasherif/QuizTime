package com.example.presentation.routes.quiz_question

import com.example.domain.repository.QuizQuestionRepository
import io.ktor.server.routing.Route
import io.ktor.server.response.*
import io.ktor.server.resources.*
import io.ktor.http.HttpStatusCode

fun Route.deleteQuizQuestionById(quizQuestionRepository: QuizQuestionRepository) {
    delete<QuestionRoutesPath.ById> { path ->
        runCatching {
            quizQuestionRepository.deleteQuestionById(path.questionId)
        }.onSuccess { isDeleted ->
            if (isDeleted) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound, "Question not found")
            }
        }.onFailure {
            call.respond(HttpStatusCode.InternalServerError, "Failed to delete question")
        }
    }
}