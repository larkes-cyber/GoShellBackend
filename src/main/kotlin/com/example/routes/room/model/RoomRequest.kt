package com.example.routes.room.model

import com.example.domain.model.TokenDTO
import kotlinx.serialization.Serializable

@Serializable
data class RoomRequest(
    val token:TokenDTO,
    val name:String,
    val image:String
)