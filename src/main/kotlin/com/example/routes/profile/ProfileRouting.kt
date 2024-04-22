package com.example.routes.profile

import com.example.domain.model.TokenDTO
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.ProfileRepository
import com.example.routes.profile.model.GetProfileRequest
import com.example.routes.profile.model.ProfileRequest
import com.example.utils.Resource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureProfileRouting(){

    val profileRepository by inject<ProfileRepository>()

    routing {
        route("/profile"){

            authenticate {
                post("/get") {
                    val request = call.receive<GetProfileRequest>()
                    call.respond(profileRepository.fetchProfile(request.login))
                }

                post("/edit") {
                    val request = call.receive<ProfileRequest>()
                    profileRepository.editProfile(request.profile)
                    call.respond(HttpStatusCode.OK)
                }
            }

        }

    }

}