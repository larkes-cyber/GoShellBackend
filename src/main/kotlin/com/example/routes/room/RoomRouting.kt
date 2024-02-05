package com.example.routes.room

import com.example.domain.model.RoomDTO
import com.example.domain.model.TokenDTO
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.DeviceRepository
import com.example.domain.repository.RoomRepository
import com.example.routes.room.model.RoomRequest
import com.example.routes.room.model.RoomResponse
import com.example.utils.Resource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRoomRouting(){

    val authRepository by inject<AuthRepository>()
    val roomRepository by inject<RoomRepository>()

    routing {

        route("/room"){

            post("/add") {
                val request = call.receive<RoomRequest>()
                val checkingTokenResult = authRepository.checkToken(request.token)
                if(checkingTokenResult is Resource.Error){
                    call.respondText(text = checkingTokenResult.message!!)
                    return@post
                }
                roomRepository.createRoom(RoomDTO(
                    name = request.name,
                    image = request.image,
                    login = request.token.login
                ))
                call.respond(HttpStatusCode.OK)
            }

            post("/get") {
                val request = call.receive<TokenDTO>()
                val checkingTokenResult = authRepository.checkToken(request)
                if(checkingTokenResult is Resource.Error){
                    call.respondText(text = checkingTokenResult.message!!)
                    return@post
                }
                call.respond(RoomResponse(roomRepository.fetchRooms(request.login)))
            }

            post("/getPhotos") {
                val request = call.receive<TokenDTO>()
                val checkingTokenResult = authRepository.checkToken(request)
                if(checkingTokenResult is Resource.Error){
                    call.respondText(text = checkingTokenResult.message!!)
                    return@post
                }
                call.respond(RoomResponse(roomRepository.fetchRoomPhotos()))
            }


        }

    }

}