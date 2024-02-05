package com.example.routes.device.model

import com.example.domain.model.TokenDTO
import kotlinx.serialization.Serializable

@Serializable
data class AddRoomDeviceRequest(
    val token: TokenDTO,
    val typeId:String,
    val roomId:String,
    var name:String? = null
)