package com.example.domain.repository

import com.example.domain.model.QuizQuestion

interface QuizQuestionRepository {
    fun upsertQuestion(question: QuizQuestion)
    fun getAllQuestions(topicCode: Int?, limit: Int?): List<QuizQuestion>
    fun getQuestionById(id: String): QuizQuestion?
    fun deleteQuestionById(id: String): Boolean
}