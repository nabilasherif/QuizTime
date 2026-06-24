package com.example.presentation.routes.quiz_topic

import com.example.domain.repository.QuizTopicRepository
import com.example.domain.util.onSuccess
import com.example.domain.util.onFailure
import com.example.presentation.util.respondWithError
import io.ktor.server.routing.Route
import io.ktor.server.response.*
import io.ktor.server.resources.*
import io.ktor.http.HttpStatusCode

fun Route.deleteQuizTopicById(quizTopicRepository: QuizTopicRepository) {
    delete<QuizTopicRoutesPath.ById> { path ->
        quizTopicRepository.deleteTopicById(path.topicId)
            .onSuccess {
                call.respond(HttpStatusCode.NoContent)
            }.onFailure { error ->
                respondWithError(error)
            }
    }
}