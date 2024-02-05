package com.example.routes.profile

import com.example.domain.model.TokenDTO
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.ProfileRepository
import com.example.routes.profile.model.ProfileRequest
import com.example.utils.Resource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureProfileRouting(){

    val profileRepository by inject<ProfileRepository>()
    val authRepository by inject<AuthRepository>()

    routing {
        route("/profile"){

            post("/get") {
                val request = call.receive<TokenDTO>()
                val checkingTokenResult = authRepository.checkToken(request)
                if(checkingTokenResult is Resource.Error){
                    call.respondText(text = checkingTokenResult.message!!)
                    return@post
                }
                call.respond(profileRepository.fetchProfile(request.login))
            }

            post("/edit") {
                val request = call.receive<ProfileRequest>()
                val checkingTokenResult = authRepository.checkToken(request.token)
                if(checkingTokenResult is Resource.Error){
                    call.respondText(text = checkingTokenResult.message!!)
                    return@post
                }
                profileRepository.editProfile(request.profile)
                call.respond(HttpStatusCode.OK)
            }

        }

    }

}