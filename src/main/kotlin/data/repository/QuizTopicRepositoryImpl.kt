package com.example.data.repository

import com.example.data.database.entity.QuizTopicEntity
import com.example.data.mapper.toQuizTopic
import com.example.data.mapper.toQuizTopicEntity
import com.example.data.util.Constant.TOPICS_COLLECTION_NAME
import com.example.domain.util.DataError
import com.example.domain.util.Result
import com.example.domain.model.QuizTopic
import com.example.domain.repository.QuizTopicRepository
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class QuizTopicRepositoryImpl(mongoDatabase: MongoDatabase) : QuizTopicRepository {

    private val topicCollection= mongoDatabase.getCollection<QuizTopicEntity>(TOPICS_COLLECTION_NAME)
    override suspend fun getAllTopics(): Result<List<QuizTopic>, DataError> {
        return try{
            val sortedQuery = Sorts.ascending(
                QuizTopicEntity::code.name
            )
            val topics= topicCollection
                .find()
                .sort(sortedQuery)
                .map { it.toQuizTopic() }
                .toList()
            if(!topics.isEmpty()){
                Result.Success(topics)
            }else{
                Result.Failure(DataError.NotFound)
            }
        }catch (e: Exception){
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun upsertTopic(topic: QuizTopic): Result<Unit, DataError> {
        return try{
            if(topic.id==null){
                topicCollection.insertOne(topic.toQuizTopicEntity())
            }else{
                val filteredQuery= Filters.eq(
                    QuizTopicEntity::_id.name,topic.id
                )
                val updateQuery= Updates.combine(
                    Updates.set(QuizTopicEntity::name.name, topic.name),
                    Updates.set(QuizTopicEntity::imageUrl.name, topic.imageUrl),
                    Updates.set(QuizTopicEntity::code.name, topic.code)
                )
                val updateResult=topicCollection.updateOne(filteredQuery, updateQuery)
                if(updateResult.modifiedCount.toInt() == 0){
                    return Result.Failure(DataError.NotFound)
                }
            }
            Result.Success(Unit)
        }catch (e: Exception){
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun getTopicById(id: String?): Result<QuizTopic, DataError> {
        if(id.isNullOrBlank()){
            Result.Failure(DataError.Validation)
        }
        return try{
            val filteredQuery= Filters.eq(
                QuizTopicEntity::_id.name, id
            )
            val topicEntity= topicCollection.find(filteredQuery).firstOrNull()
            if (topicEntity!=null){
                Result.Success(topicEntity.toQuizTopic())
            }else{
                Result.Failure(DataError.NotFound)
            }
        }catch (e: Exception){
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun deleteTopicById(id: String?): Result<Unit, DataError> {
        if(id.isNullOrBlank()){
            Result.Failure(DataError.Validation)
        }
        return try{
            val filteredQuery= Filters.eq(QuizTopicEntity::_id.name, id)
            val deleteResult= topicCollection.deleteOne(filteredQuery)
            if(deleteResult.deletedCount>0){
                Result.Success(Unit)
            }else{
                Result.Failure(DataError.NotFound)
            }

        }catch (e: Exception){
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }
}