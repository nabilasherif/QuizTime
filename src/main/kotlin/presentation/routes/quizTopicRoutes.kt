package com.example.presentation.routes

import com.example.domain.model.QuizTopic
import com.example.domain.repository.QuizTopicRepository
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.routes.path.QuizTopicRoutesPath
import com.example.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.quizTopicRoutes(
    quizTopicRepository: QuizTopicRepository
) {
    get<QuizTopicRoutesPath> { path ->
        quizTopicRepository.getAllTopics()
            .onSuccess { topics ->
                call.respond(HttpStatusCode.OK, topics)
            }.onFailure { error ->
                respondWithError(error)
            }
    }

    post<QuizTopicRoutesPath> {
        val topic = call.receive<QuizTopic>()
        quizTopicRepository.upsertTopic(topic)
            .onSuccess {
                call.respond(HttpStatusCode.Created, "Topic added successfully")
            }.onFailure { error ->
                respondWithError(error)
            }
    }

    get<QuizTopicRoutesPath.ById> { path ->
        quizTopicRepository.getTopicById(path.topicId)
            .onSuccess { topic ->
                call.respond(HttpStatusCode.OK, topic)
            }.onFailure { error ->
                respondWithError(error)
            }
    }
    delete<QuizTopicRoutesPath.ById> { path ->
        quizTopicRepository.deleteTopicById(path.topicId)
            .onSuccess {
                call.respond(HttpStatusCode.NoContent)
            }.onFailure { error ->
                respondWithError(error)
            }
    }
}