package com.example.routes.room.model

import kotlinx.serialization.Serializable

@Serializable
data class RoomResponse<T>(
    val list:List<T>
)