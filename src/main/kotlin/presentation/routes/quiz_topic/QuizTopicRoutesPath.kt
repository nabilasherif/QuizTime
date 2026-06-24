package com.example.presentation.routes.quiz_topic

import io.ktor.resources.*

@Resource(path = "quiz/topics")
data class QuizTopicRoutesPath(
    val topicCode: Int? = null,
    val limit: Int? = null,
) {
    @Resource(path = "/{topicId}")
    data class ById(
        val parent: QuizTopicRoutesPath = QuizTopicRoutesPath(),
        val topicId: String
    )
}