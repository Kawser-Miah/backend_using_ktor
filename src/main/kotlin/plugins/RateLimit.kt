package com.example.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.ratelimit.RateLimit
import io.ktor.server.plugins.ratelimit.RateLimitName
import kotlin.time.Duration.Companion.seconds
val unlimitedTypes = setOf("internal", "trusted")

fun Application.configureRateLimit(){
    install(RateLimit){
//        global { rateLimiter(limit = 5, refillPeriod = 60.seconds) } }
        register(RateLimitName("public")) { rateLimiter(limit = 5, refillPeriod = 60.seconds) }
        register(RateLimitName("private")) { rateLimiter(limit = 10, refillPeriod = 60.seconds)
            requestKey { call ->
               call.request.queryParameters["type"] ?: ""

            }
            requestWeight { call, key ->
                when (key) {
                    in unlimitedTypes -> 0
                    "admin" -> 2
                    else -> 1
                }
            }

        } }
    }
