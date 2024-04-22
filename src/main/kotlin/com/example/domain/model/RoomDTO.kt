package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RoomDTO(
    val id:String? = null,
    val image:String,
    val name:String,
    val userId:String
)