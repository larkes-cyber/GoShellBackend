package com.example.routes.auth.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegRequest(
    val name:String,
    val login:String,
    val password:String
)