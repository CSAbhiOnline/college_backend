package com.example

import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.CORS

fun main(args: Array<String>) {
    io.ktor.server.cio.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    //configureHTTP()
    configureRouting()
    Databasefactory.init()

    install(CORS){
        anyHost()
        allowHeader(HttpHeaders.ContentType)
    }

}
