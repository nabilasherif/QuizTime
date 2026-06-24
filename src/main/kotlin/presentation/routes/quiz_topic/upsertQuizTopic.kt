package com.example.presentation.routes.quiz_topic

import com.example.domain.model.QuizTopic
import com.example.domain.repository.QuizTopicRepository
import com.example.domain.util.onSuccess
import com.example.domain.util.onFailure
import com.example.presentation.util.respondWithError
import io.ktor.server.routing.Route
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.http.HttpStatusCode

fun Route.upsertQuizTopic(quizTopicRepository: QuizTopicRepository) {
    post<QuizTopicRoutesPath> {
        val topic = call.receive<QuizTopic>()
        quizTopicRepository.upsertTopic(topic)
            .onSuccess {
                call.respond(HttpStatusCode.Created, "Topic added successfully")
            }.onFailure { error ->
                respondWithError(error)
            }
    }
}