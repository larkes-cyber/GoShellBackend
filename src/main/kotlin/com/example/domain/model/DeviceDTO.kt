package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DeviceDTO(
    val id:String,
    val name:String,
    val icon:String
)