package com.example

import com.example.di.appModule
import com.example.plugins.*
import com.example.routes.auth.configureAuthRouting
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

    install(Koin){
        modules(appModule)
    }
}
