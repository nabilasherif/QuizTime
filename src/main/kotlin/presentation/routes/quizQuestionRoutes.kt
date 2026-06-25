package com.example.presentation.routes

import com.example.domain.model.QuizQuestion
import com.example.domain.repository.QuizQuestionRepository
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import com.example.presentation.routes.path.QuizQuestionRoutesPath
import com.example.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.quizQuestionRoutes(
    repository: QuizQuestionRepository
) {
    get<QuizQuestionRoutesPath> { path ->
        repository.getAllQuestions(path.topicCode, path.limit)
            .onSuccess { questions ->
                call.respond(HttpStatusCode.OK, questions)
            }.onFailure { error ->
                respondWithError(error)
            }
    }

    post<QuizQuestionRoutesPath> {
        val question = call.receive<QuizQuestion>()
        repository.upsertQuestion(question)
            .onSuccess {
                call.respond(HttpStatusCode.Created, "Question added successfully")
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }

    get<QuizQuestionRoutesPath.ById> { path ->
        repository.getQuestionById(path.questionId)
            .onSuccess { question ->
                call.respond(HttpStatusCode.OK, question)
            }.onFailure { error ->
                respondWithError(error)
            }
    }

    delete<QuizQuestionRoutesPath.ById> { path ->
        repository.deleteQuestionById(path.questionId)
            .onSuccess {
                call.respond(HttpStatusCode.NoContent)
            }.onFailure { error ->
                respondWithError(error)
            }
    }
}