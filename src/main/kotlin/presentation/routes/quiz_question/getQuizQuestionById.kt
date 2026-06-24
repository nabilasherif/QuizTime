package com.example.presentation.routes.quiz_question

import com.example.domain.repository.QuizQuestionRepository
import com.example.domain.util.onSuccess
import com.example.domain.util.onFailure
import com.example.presentation.util.respondWithError
import io.ktor.server.routing.Route
import io.ktor.server.response.*
import io.ktor.server.resources.*
import io.ktor.http.HttpStatusCode

fun Route.getQuizQuestionById(quizQuestionRepository: QuizQuestionRepository) {
    get<QuestionRoutesPath.ById> { path ->
        quizQuestionRepository.getQuestionById(path.questionId)
            .onSuccess { question ->
                call.respond(HttpStatusCode.OK, question)
            }.onFailure { error ->
                respondWithError(error)
            }
    }
}