package com.example

import com.example.ADMIN.AdminRoutes
import com.example.USER.UserRoutes
import com.sun.tools.javac.resources.CompilerProperties.Fragments.Static
import io.ktor.server.application.*

import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {

        AdminRoutes()
        UserRoutes()
    }
}
