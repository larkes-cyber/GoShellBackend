package com.example

import com.example.di.appModule
import com.example.plugins.*
import com.example.routes.auth.configureAuthRouting
import com.example.routes.device.configureDeviceRouting
import com.example.routes.profile.configureProfileRouting
import com.example.routes.room.configureRoomRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.plugin.Koin

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureAuthRouting()
    configureRoomRouting()
    configureDeviceRouting()
    configureProfileRouting()

    install(Koin){
        modules(appModule)
    }
}
