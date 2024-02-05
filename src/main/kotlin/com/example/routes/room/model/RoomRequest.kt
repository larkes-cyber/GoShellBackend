package com.example.routes.room.model

import com.example.domain.model.TokenDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoomRequest(
    @SerialName("token")
    val token:TokenDTO,
    @SerialName("name")
    val name:String,
    @SerialName("image")
    val image:String
)