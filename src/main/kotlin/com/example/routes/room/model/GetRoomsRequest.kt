package com.example.routes.room.model

import kotlinx.serialization.Serializable

@Serializable
data class GetRoomsRequest(
    val id:String
)