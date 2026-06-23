package com.example.presentation.routes

import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Route.root() {
    get("/") {
        call.respondText("welcome to the Quiz API")
    }
}