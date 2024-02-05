package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class HomeDevicesDTO(
    val typeId:String,
    val name:String,
    val active:Int,
    val inactive:Int
)