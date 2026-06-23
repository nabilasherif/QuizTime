package com.example.data.repository

import com.example.data.database.entity.QuizQuestionEntity
import com.example.data.mapper.toQuizQuestion
import com.example.data.mapper.toQuizQuestionEntity
import com.example.data.util.Constant.QUESTIONS_COLLECTION_NAME
import com.example.domain.model.QuizQuestion
import com.example.domain.repository.QuizQuestionRepository
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.mongodb.client.model.Updates
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class QuizQuestionRepositoryImpl(mongoDatabase: MongoDatabase) : QuizQuestionRepository {

    private val questionCollection = mongoDatabase.getCollection<QuizQuestionEntity>(QUESTIONS_COLLECTION_NAME)

    override suspend fun upsertQuestion(question: QuizQuestion) {
        try {
            if (question.id == null) {
                questionCollection.insertOne(question.toQuizQuestionEntity())
            } else {
                val filterQuery = Filters.eq("_id", question.id)
                val updateQuery = Updates.combine(
                    Updates.set(QuizQuestionEntity::question.name, question.question),
                    Updates.set(QuizQuestionEntity::correctAnswer.name, question.correctAnswer),
                    Updates.set(QuizQuestionEntity::incorrectAnswer.name, question.incorrectAnswer),
                    Updates.set(QuizQuestionEntity::explanation.name, question.explanation),
                    Updates.set(QuizQuestionEntity::topicCode.name, question.topicCode),
                )
                questionCollection.updateOne(filterQuery, updateQuery)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getAllQuestions(
        topicCode: Int?,
        limit: Int?
    ): List<QuizQuestion> {
        return try {
            val questionLimit = limit?.takeIf { it > 0 } ?: 10
            val filterQuery = topicCode?.let {
                Filters.eq(QuizQuestionEntity::topicCode.name, it)
            } ?: Filters.empty()

            questionCollection.find(filterQuery).limit(questionLimit).map { it.toQuizQuestion() }.toList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getQuestionById(id: String): QuizQuestion? {
        return try {
            val filterQuery = Filters.eq("_id", id)
            val questionEntity = questionCollection.find(filter = filterQuery).firstOrNull()
            questionEntity?.toQuizQuestion()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun deleteQuestionById(id: String): Boolean {
        return try {
            val filterQuery = Filters.eq("_id", id)
            val deleteResult = questionCollection.deleteOne(filter = filterQuery)
            deleteResult.deletedCount > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}