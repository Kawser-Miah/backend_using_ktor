package com.example

import com.example.plugins.configureAutoHeadResponse
import com.example.plugins.configureBasicAuthentication
import com.example.plugins.configureDigestAuthentication
import com.example.plugins.configurePartialContent
import com.example.plugins.configureRateLimit
import com.example.plugins.configureRequestValidation
import com.example.plugins.configureResources
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.plugins.configureStatusPage
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureResources()
    //configureRateLimit should be before configureRouting
    configureRateLimit()
//    configureBasicAuthentication()
    configureDigestAuthentication()
    configureRouting()
//    configureStatusPage()
    configureRequestValidation()
    configureAutoHeadResponse()
    configurePartialContent()

}
