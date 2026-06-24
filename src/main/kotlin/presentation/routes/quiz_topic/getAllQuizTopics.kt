package com.example.presentation.routes.quiz_topic

import com.example.domain.repository.QuizTopicRepository
import com.example.domain.util.onSuccess
import com.example.domain.util.onFailure
import com.example.presentation.util.respondWithError
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Route
import io.ktor.server.response.*
import io.ktor.server.resources.*

fun Route.getAllQuizTopics(quizTopicRepository: QuizTopicRepository) {
    get<QuizTopicRoutesPath> { path ->
        quizTopicRepository.getAllTopics()
            .onSuccess { topics ->
                call.respond(HttpStatusCode.OK, topics)
            }.onFailure { error ->
                respondWithError(error)
            }
    }
}