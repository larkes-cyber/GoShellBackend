package com.example.data.source.device.database

import com.example.data.source.device.model.RoomDeviceEntity

interface DeviceDatabaseDataSource {

    suspend fun insertRoomDevice(roomDeviceEntity: RoomDeviceEntity)
    suspend fun fetchRoomDevices(roomId:String, login:String):List<RoomDeviceEntity>
    suspend fun fetchRoomDevice(id:String):RoomDeviceEntity?
    suspend fun fetchHomeDevices(login:String):List<RoomDeviceEntity>
    suspend fun replaceRoomDevice(roomDeviceEntity: RoomDeviceEntity)

}