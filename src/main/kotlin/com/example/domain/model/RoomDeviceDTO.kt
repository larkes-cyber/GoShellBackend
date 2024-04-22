package com.example.domain.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class RoomDeviceDTO(
    val id:String? = null,
    val typeId:String,
    val roomId:String,
    val userId:String,
    val active:Boolean = false,
    val icon:String? = null,
    var name:String? = null
)