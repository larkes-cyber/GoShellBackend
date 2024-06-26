package com.example.routes.device

import com.example.domain.model.RoomDeviceDTO
import com.example.domain.model.TokenDTO
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.DeviceRepository
import com.example.routes.device.model.*
import com.example.utils.Resource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.io.File

fun Application.configureDeviceRouting(){

    val authRepository by inject<AuthRepository>()
    val deviceRepository by inject<DeviceRepository>()

    routing {

        route("/device"){

            authenticate {
                post("/get") {
                    call.respond(deviceRepository.fetchDevices())
                }

                post("/getRoom") {
                    val request = call.receive<GetRoomDevicesRequest>()

                    val principal = call.principal<JWTPrincipal>()
                    val userId = principal!!.payload.getClaim("userId").asString()

                    call.respond(deviceRepository.fetchRoomDevices(userId = userId, roomId = request.roomId))
                }

                post("/addRoom") {
                    val request = call.receive<AddRoomDeviceRequest>()

                    val principal = call.principal<JWTPrincipal>()
                    val userId = principal!!.payload.getClaim("userId").asString()

                    deviceRepository.insertRoomDevice(userId, RoomDeviceDTO(
                        name = request.name,
                        roomId = request.roomId,
                        typeId = request.typeId
                    ))
                    call.respond(HttpStatusCode.OK)
                }

                post("/switchActive") {
                    val request = call.receive<DeviceActiveRequest>()
                    deviceRepository.switchDeviceActive(request.id)
                    call.respond(HttpStatusCode.OK)
                }

                post("/switchDevicesActive") {
                    val request = call.receive<DeviceActiveRequest>()
                    val principal = call.principal<JWTPrincipal>()
                    val userId = principal!!.payload.getClaim("userId").asString()
                    deviceRepository.switchDevicesActive(userId = userId, id = request.id)
                    call.respond(HttpStatusCode.OK)
                }

                post("/getHome"){
                    val principal = call.principal<JWTPrincipal>()
                    val userId = principal!!.payload.getClaim("userId").asString()
                    call.respond(DevicesResponse(deviceRepository.fetchHomeDevices(userId)))

                }

            }

            get("/icon"){
                val filename = call.parameters["name"]!!
                val file = File("iconsSrc/$filename.png")
                call.respondFile(file)
            }

        }

    }

}