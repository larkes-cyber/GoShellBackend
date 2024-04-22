package com.example.data.source.device.model

data class RoomDeviceEntity(
    val id:String,
    val typeId:String,
    val roomId:String,
    val userId:String,
    var active:Boolean,
    val name:String
)