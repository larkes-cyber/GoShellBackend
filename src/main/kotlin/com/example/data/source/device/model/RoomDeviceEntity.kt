package com.example.data.source.device.model

data class RoomDeviceEntity(
    val id:String,
    val typeId:String,
    val roomId:String,
    val login:String,
    var active:Boolean,
    val name:String
)