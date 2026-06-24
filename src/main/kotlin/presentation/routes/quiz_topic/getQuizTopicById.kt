package com.example.presentation.routes.quiz_topic

import com.example.domain.repository.QuizTopicRepository
import com.example.domain.util.onSuccess
import com.example.domain.util.onFailure
import com.example.presentation.util.respondWithError
import io.ktor.server.routing.Route
import io.ktor.server.response.*
import io.ktor.server.resources.*
import io.ktor.http.HttpStatusCode

fun Route.getQuizTopicById(quizTopicRepository: QuizTopicRepository) {
    get<QuizTopicRoutesPath.ById> { path ->
        quizTopicRepository.getTopicById(path.topicId)
            .onSuccess { topic ->
                call.respond(HttpStatusCode.OK, topic)
            }.onFailure { error ->
                respondWithError(error)
            }
    }
}