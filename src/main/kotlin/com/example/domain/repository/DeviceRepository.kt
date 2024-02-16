package com.example.domain.repository

import com.example.domain.model.DeviceDTO
import com.example.domain.model.HomeDevicesDTO
import com.example.domain.model.RoomDeviceDTO
import com.example.domain.model.TokenDTO

interface DeviceRepository {

    suspend fun insertRoomDevice(roomDeviceDTO: RoomDeviceDTO)
    suspend fun fetchDevices():List<DeviceDTO>
    suspend fun fetchRoomDevices(roomId:String, login:String):List<RoomDeviceDTO>
    suspend fun switchDeviceActive(id:String)
    suspend fun switchDevicesActive(login:String, id:String)
    suspend fun fetchHomeDevices(login:String):List<HomeDevicesDTO>

}