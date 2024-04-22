package com.example.routes.room.model

import com.example.domain.model.TokenDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoomRequest(
    val name:String,
    val image:String,
    val login:String
)