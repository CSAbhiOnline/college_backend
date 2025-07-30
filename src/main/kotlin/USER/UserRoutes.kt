package com.example.USER

import com.example.DATA_CLASSES.User
import com.example.SERVICES.CollegeService
import com.example.SERVICES.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.UserRoutes() {
    val collegeService= CollegeService()
    route("/user") {
        get("/all-colleges") {
            val colleges = collegeService.getAllColleges()
            call.respond(colleges)
        }
        get("/colleges/id/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                return@get
            }
            val college = collegeService.getCollegeById(id)
            if (college == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(college)
            }
        }
        get("/colleges/name/{name}") {
            val name = call.parameters["name"]
            if (name == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid name")
                return@get
            }
            val colleges = collegeService.getCollegesByName(name)
            call.respond(colleges)
        }
        get("/colleges/category/{category}") {
            val category = call.parameters["category"]
            if (category == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid category")
                return@get
            }
            val colleges = collegeService.getCollegesByCategory(category)
            call.respond(colleges)
        }
        get("/colleges/state/{state}") {
            val state = call.parameters["state"]
            if (state == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid state")
                return@get
            }
            val colleges = collegeService.getCollegesByState(state)
            call.respond(colleges)
        }
        get("/colleges/city/{city}") {
            val city = call.parameters["city"]
            if (city == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid city")
                return@get
            }
            val colleges = collegeService.getCollegesByCity(city)
            call.respond(colleges)
        }
    }
}