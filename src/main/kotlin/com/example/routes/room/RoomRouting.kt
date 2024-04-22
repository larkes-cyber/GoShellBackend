package com.example.routes.room

import com.example.domain.model.RoomDTO
import com.example.domain.model.TokenDTO
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.DeviceRepository
import com.example.domain.repository.RoomRepository
import com.example.routes.room.model.GetRoomsRequest
import com.example.routes.room.model.RoomRequest
import com.example.routes.room.model.RoomResponse
import com.example.utils.Resource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.UUID

fun Application.configureRoomRouting(){

    val roomRepository by inject<RoomRepository>()

    routing {

        route("/room"){

            authenticate {
                post("/add") {
                    val request = call.receive<RoomRequest>()
                    val uniqId = UUID.randomUUID().toString()
                    roomRepository.createRoom(RoomDTO(
                        id = uniqId,
                        name = request.name,
                        image = request.image,
                        login = request.login
                    ))
                    call.respondText(status = HttpStatusCode.OK, text = uniqId)
                }

                post("/get") {
                    val request = call.receive<GetRoomsRequest>()
                    val fromQ = call.parameters["from"]?.toInt() ?: return@post call.respond(HttpStatusCode.BadRequest)
                    val toQ = call.parameters["to"]?.toInt() ?: return@post call.respond(HttpStatusCode.BadRequest)
                    val rooms = roomRepository.fetchRooms(request.login)
                    val leftSlice = fromQ
                    val rightSlice = if(toQ >= rooms.size) rooms.size - 1 else toQ

                    if(leftSlice >= rooms.size){
                        call.respond(RoomResponse(emptyList<RoomDTO>()))
                        return@post
                    }
                    call.respond(RoomResponse(rooms.asReversed().subList(leftSlice, rightSlice + 1)))
                }

                post("/getPhotos") {
                    call.respond(RoomResponse(roomRepository.fetchRoomPhotos()))
                }
            }


        }

    }

}