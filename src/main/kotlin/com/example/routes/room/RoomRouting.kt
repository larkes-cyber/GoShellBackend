package com.example.routes.room

import com.example.domain.model.RoomDTO
import com.example.domain.repository.RoomRepository
import com.example.routes.room.model.RoomRequest
import com.example.routes.room.model.RoomResponse
import com.example.routes.room.model.RoomsResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
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

                    val principal = call.principal<JWTPrincipal>()
                    val userId = principal!!.payload.getClaim("userId").asString()

                    val uniqId = UUID.randomUUID().toString()

                    roomRepository.createRoom(RoomDTO(
                        id = uniqId,
                        name = request.name,
                        image = request.image,
                        userId = userId
                    ))

                    call.respondText(uniqId, status = HttpStatusCode.OK)
                }

                post("/get") {
                    val count = call.parameters["count"]?.toInt() ?: return@post call.respond(HttpStatusCode.BadRequest)
                    val principal = call.principal<JWTPrincipal>()
                    val userId = principal!!.payload.getClaim("userId").asString()
                    val rooms = roomRepository.fetchRooms(userId).map { RoomResponse(
                        id = it.id,
                        image = it.image,
                        name = it.name
                    ) }
                    println(rooms + " dfgdfgdfgdg")
                    if(count > rooms.size) {
                        call.respond(RoomsResponse(rooms.asReversed()))
                        return@post
                    }
                    call.respond(RoomsResponse(rooms.asReversed().subList(0,count)))
                }

                post("/getPhotos") {
                    call.respond(RoomsResponse(roomRepository.fetchRoomPhotos()))
                }
            }


        }

    }

}