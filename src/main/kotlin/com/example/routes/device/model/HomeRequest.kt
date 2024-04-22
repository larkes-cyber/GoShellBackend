package com.example.routes.device.model

import kotlinx.serialization.Serializable

@Serializable
data class HomeRequest(
    val login:String
)