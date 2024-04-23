package com.example.routes.room.model

import kotlinx.serialization.Serializable

@Serializable
data class RoomsResponse<T>(
    val list:List<T>
)