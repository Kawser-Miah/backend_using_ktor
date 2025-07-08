package com.example.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.plugins.statuspages.statusFile
import io.ktor.server.response.respond
import io.ktor.server.response.respondText

fun Application.configureStatusPage(){
    install(StatusPages){
//        exception<Throwable>{
//            call,cause->
//            call.respondText("500: ${cause.message}", status = HttpStatusCode.InternalServerError)
//        }
        exception<RequestValidationException>{
            call,cause->
            call.respond(HttpStatusCode.BadRequest, mapOf("errors" to cause.reasons))
        }

        status(HttpStatusCode.Unauthorized) {call,cause->
            call.respondText("401: You are not authorized for this", status = HttpStatusCode.Unauthorized)
        }
        status(HttpStatusCode.BadRequest) {call,cause->
            call.respondText("400: Please check your body", status = HttpStatusCode.BadRequest)
        }
        status(HttpStatusCode.InternalServerError) {call,cause->
            call.respondText("500: cause", status = HttpStatusCode.InternalServerError)
        }

        statusFile(HttpStatusCode.BadRequest, HttpStatusCode.Unauthorized, HttpStatusCode.NotFound, filePattern = "errors/error#.html")
    }
}