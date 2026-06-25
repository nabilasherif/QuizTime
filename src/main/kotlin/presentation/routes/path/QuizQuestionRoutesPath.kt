package com.example.presentation.routes.path

import io.ktor.resources.*

@Resource(path="quiz/questions")
class QuizQuestionRoutesPath (
    val topicCode: Int?=null,
    val limit: Int?=null,
){
    @Resource(path="/{questionId}")
    data class ById(
        val parent: QuizQuestionRoutesPath = QuizQuestionRoutesPath(),
        val questionId: String
    )
}