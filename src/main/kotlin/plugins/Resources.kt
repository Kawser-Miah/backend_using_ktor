package com.example.plugins


//import io.ktor.http.ContentType.Application
import io.ktor.server.application.*
import io.ktor.server.application.install
import io.ktor.server.resources.*

fun Application.configureResources(){
    install(Resources)
}


