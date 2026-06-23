package com.example.data.mapper

import com.example.data.database.entity.QuizQuestionEntity
import com.example.domain.model.QuizQuestion

fun QuizQuestionEntity.toQuizQuestion() = QuizQuestion(
    id =  _id,
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswer = incorrectAnswer,
    explanation = explanation,
    topicCode = topicCode
)

fun QuizQuestion.toQuizQuestionEntity() = QuizQuestionEntity(
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswer = incorrectAnswer,
    explanation = explanation,
    topicCode = topicCode
)