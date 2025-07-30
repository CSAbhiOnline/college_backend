package com.example.ADMIN

import com.example.DATA_CLASSES.College
import com.example.DATA_CLASSES.User
import com.example.SERVICES.CollegeService
import com.example.SERVICES.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.AdminRoutes() {
    val collegeService = CollegeService()
    val userService = UserService()

    route("/admin") {

        post("/create-user") {
            val user = call.receive<User>()
            val createdUser = userService.createUser(user)
            if (createdUser != null) {
                call.respond(HttpStatusCode.Created, createdUser)
            } else {
                call.respond(HttpStatusCode.InternalServerError, "failed to create user")
            }
        }


        post("/add-college") {
            val college = call.receive<College>()
            collegeService.addCollege(college)
            call.respond("College added successfully")
        }

        delete("/delete-college/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "invalid id")
                return@delete
            }

            val isDeleted = collegeService.deleteCollege(id)
            if (isDeleted) {
                call.respond(HttpStatusCode.OK, "college with id $id is deleted")
            } else {
                call.respond(HttpStatusCode.NotFound, "college with id $id not found")
            }
        }


    }
}