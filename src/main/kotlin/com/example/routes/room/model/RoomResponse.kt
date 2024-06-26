package com.example.routes.room.model

import kotlinx.serialization.Serializable

@Serializable
data class RoomResponse(
    val id:String? = null,
    val image:String,
    val name:String,
)