package com.example.routes.profile

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.example.domain.model.TokenDTO
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.ProfileRepository
import com.example.routes.profile.model.GetProfileRequest
import com.example.routes.profile.model.ProfileRequest
import com.example.security.token.JwtTokenService
import com.example.utils.Resource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
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
                    val principal = call.principal<JWTPrincipal>()
                    val userId = principal!!.payload.getClaim("userId").asString()
                    call.respond(profileRepository.fetchProfile(userId)!!)
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