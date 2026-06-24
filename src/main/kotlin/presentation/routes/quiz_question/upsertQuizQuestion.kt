package com.example.presentation.routes.quiz_question

import com.example.domain.model.QuizQuestion
import com.example.domain.repository.QuizQuestionRepository
import io.ktor.server.routing.Route
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.http.HttpStatusCode

fun Route.upsertQuizQuestion(quizQuestionRepository: QuizQuestionRepository) {
    post<QuestionRoutesPath> {
        val question = call.receive<QuizQuestion>()
        quizQuestionRepository.upsertQuestion(question)
        call.respond(HttpStatusCode.Created, "Question added successfully")
    }
}