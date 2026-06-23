package com.example.data.repository

import com.example.domain.model.QuizQuestion
import com.example.domain.repository.QuizQuestionRepository

class QuizQuestionRepositoryImpl : QuizQuestionRepository {
    private val quizQuestions = mutableListOf<QuizQuestion>()

    override fun upsertQuestion(question: QuizQuestion) {
        quizQuestions.add(question)
    }

    override fun getAllQuestions(topicCode: Int?, limit: Int?): List<QuizQuestion> {
        return quizQuestions
            .let { if (topicCode != null) it.filter { q -> q.topicCode == topicCode } else it }
            .take(limit ?: quizQuestions.size)
    }

    override fun getQuestionById(id: String): QuizQuestion? {
        return quizQuestions.find { it.id == id }
    }

    override fun deleteQuestionById(id: String): Boolean {
        return quizQuestions.removeIf { it.id == id }
    }
}