package com.example.data.source.device.database

import com.example.data.source.device.model.RoomDeviceEntity

interface DeviceDatabaseDataSource {

    suspend fun insertRoomDevice(roomDeviceEntity: RoomDeviceEntity)
    suspend fun fetchRoomDevices(roomId:String, userId:String):List<RoomDeviceEntity>
    suspend fun fetchRoomDevice(id:String):RoomDeviceEntity?
    suspend fun fetchHomeDevices(id:String):List<RoomDeviceEntity>
    suspend fun replaceRoomDevice(roomDeviceEntity: RoomDeviceEntity)
    suspend fun fetchUserDevices(id:String):List<RoomDeviceEntity>

}