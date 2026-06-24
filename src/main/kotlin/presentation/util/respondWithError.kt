package com.example.presentation.util

import com.example.domain.util.DataError
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.RoutingContext
import io.ktor.server.response.respond


suspend fun RoutingContext.respondWithError(
    error: DataError
){
    when(error){
        DataError.Database ->{
            call.respond(
                HttpStatusCode.InternalServerError,
                "An unknown error occurred"
            )
        }
        DataError.NotFound ->{
            call.respond(
                HttpStatusCode.NotFound,
                "Resource Not Found"
            )
        }
        DataError.Unknown ->{
            call.respond(
                HttpStatusCode.InternalServerError,
                "An unknown error occurred"
            )
        }
        DataError.Validation ->{
            call.respond(
                HttpStatusCode.BadRequest,
                "Invalid data provided"
            )
        }
    }
}