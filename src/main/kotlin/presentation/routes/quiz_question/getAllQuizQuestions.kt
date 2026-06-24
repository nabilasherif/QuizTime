package com.example.presentation.routes.quiz_question

import com.example.domain.repository.QuizQuestionRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Route
import io.ktor.server.response.*
import io.ktor.server.resources.*

fun Route.getAllQuizQuestions(quizQuestionRepository: QuizQuestionRepository) {
    get<QuestionRoutesPath> { path ->
        runCatching {
            quizQuestionRepository.getAllQuestions(path.topicCode, path.limit)
        }.onSuccess { questions ->
            if (questions.isEmpty()) {
                call.respond(HttpStatusCode.NotFound, "No questions found")
            } else {
                call.respond(HttpStatusCode.OK, questions)
            }
        }.onFailure {
            call.respond(HttpStatusCode.InternalServerError, "Failed to retrieve questions")
        }
    }
}