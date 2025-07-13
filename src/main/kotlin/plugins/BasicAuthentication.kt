package com.example.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserHashedTableAuth
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.basic
import io.ktor.util.getDigestFunction

fun Application.configureBasicAuthentication(){

    val hashedUserTable = createHashedUserTable()
    install(Authentication){
        basic("auth-basic"){
            validate { credentials->
//                val name = credentials.name
//                val password = credentials.password
//
//                if (name == "admin" && password == "pass"){
//                    UserIdPrincipal(name)
//                }else{
//                    null
//                }
                hashedUserTable.authenticate(credentials)
            }
        }
    }
}

fun createHashedUserTable () : UserHashedTableAuth{
    val digester = getDigestFunction("SHA-256"){"ktor${it.length}"}
    return UserHashedTableAuth(
        digester = digester,
        table = mapOf(
            "admin" to digester("pass"),
            "user1" to digester("pass1"),
            "user2" to digester("pass2")
        )
    )
}