package com.example.data.database

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase

object DatabaseFactory {
    fun create(): MongoDatabase{
        val connectionString= "mongodb+srv://nabilasherif81_db_user:DQ5n8Wo6tmKzPcvH@cluster0.ls0qz5e.mongodb.net/?appName=Cluster0"
        val databaseName= "QuizTime_db"
        val mongoClient= MongoClient.create(connectionString)
        return mongoClient.getDatabase(databaseName)
    }
}