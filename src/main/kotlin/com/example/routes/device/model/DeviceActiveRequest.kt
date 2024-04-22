package com.example.routes.device.model

import com.example.domain.model.TokenDTO
import kotlinx.serialization.Serializable

@Serializable
data class DeviceActiveRequest(
    val id:String
)