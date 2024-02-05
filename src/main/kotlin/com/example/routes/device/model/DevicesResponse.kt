package com.example.routes.device.model

import com.example.domain.model.DeviceDTO
import kotlinx.serialization.Serializable

@Serializable
class DevicesResponse<T>(
    val devices:List<T>
)