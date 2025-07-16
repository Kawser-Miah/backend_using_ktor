package com.example.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.digest
import java.security.MessageDigest

const val Realm = "Access protected routes"

val userTable: Map<String, ByteArray> = mapOf(
    "admin" to getMD5Digest("admin:${Realm}:pass"),
    "user1" to getMD5Digest("user1:${Realm}:pass1"),
    "user2" to getMD5Digest("user2:${Realm}:pass2")
)

fun getMD5Digest(text: String): ByteArray {
    return MessageDigest.getInstance("MD5").digest(text.toByteArray())
}
fun Application.configureDigestAuthentication(){
    install(Authentication){
        digest ("digest-auth"){
            realm = Realm

            digestProvider { userName, realm ->
                userTable[userName]
            }
            validate { credentials->
                if (credentials.userName.isNotBlank()){
                    UserIdPrincipal(credentials.userName)
                }else{
                    null
                }
            }
        }
    }
}