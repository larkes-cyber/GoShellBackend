package com.example.domain.model

import java.util.UUID

data class RoomDeviceDTO(
    val id:String? = null,
    val typeId:String,
    val roomId:String,
    val login:String,
    val active:Boolean = false,
    val icon:String? = null,
    var name:String? = null
)