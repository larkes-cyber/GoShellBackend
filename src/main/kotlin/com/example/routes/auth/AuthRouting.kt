package com.example.routes.auth

import com.example.domain.model.ProfileDTO
import com.example.domain.repository.AuthRepository
import com.example.routes.auth.model.AuthRequest
import com.example.routes.auth.model.RegRequest
import com.example.utils.Resource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureAuthRouting(){

    val authRepository by inject<AuthRepository>()

    routing() {
        post("/auth/login"){
            val request = call.receive<AuthRequest>()
            val token = authRepository.performLogin(login = request.login, password = request.password)
            if(token is Resource.Success){
                call.respond(token.data!!)
                return@post
            }
            call.respondText(text = token.message!!, status = HttpStatusCode.NotAcceptable)
        }
        post("/auth/registration"){
            val request = call.receive<RegRequest>()
            val token = authRepository.performRegistration(ProfileDTO(
                name = request.name,
                password = request.password,
                login = request.login
            ))
            if(token is Resource.Success){
                call.respond(token.data!!)
                return@post
            }
            call.respondText(text = token.message!!, status = HttpStatusCode.NotAcceptable)
        }
    }

}