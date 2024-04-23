package com.example.data.repository

import com.example.data.repository.mapper.toDeviceDTO
import com.example.data.repository.mapper.toRoomDeviceEntity
import com.example.data.source.device.database.DeviceDatabaseDataSource
import com.example.data.source.device.model.RoomDeviceEntity
import com.example.data.source.device.storage.DeviceStaticStorageDataSource
import com.example.domain.model.DeviceDTO
import com.example.domain.model.HomeDevicesDTO
import com.example.domain.model.RoomDeviceDTO
import com.example.domain.repository.DeviceRepository

class DeviceRepositoryImpl(
    private val deviceDatabaseDataSource: DeviceDatabaseDataSource,
    private val deviceStaticStorageDataSource: DeviceStaticStorageDataSource
):DeviceRepository {
    override suspend fun insertRoomDevice(userId: String, roomDeviceDTO: RoomDeviceDTO) {
        if(roomDeviceDTO.name == null){
            val device = deviceStaticStorageDataSource.fetchDevice(roomDeviceDTO.typeId)
            roomDeviceDTO.name = device.name
        }
        deviceDatabaseDataSource.insertRoomDevice(roomDeviceDTO.toRoomDeviceEntity(userId))
    }

    override suspend fun fetchDevices(): List<DeviceDTO> {
        return deviceStaticStorageDataSource.fetchDevices().map { it.toDeviceDTO() }
    }

    override suspend fun fetchRoomDevices(roomId: String, userId:String): List<RoomDeviceDTO> {
        return deviceDatabaseDataSource.fetchRoomDevices(
            roomId = roomId,
            userId = userId
        ).map {
            val device = deviceStaticStorageDataSource.fetchDevice(it.typeId)
            RoomDeviceDTO(
                id = it.id,
                typeId = it.typeId,
                roomId = it.roomId,
                active = it.active,
                icon = device.icon,
                name = it.name
            )
        }
    }

    override suspend fun switchDeviceActive(id: String) {
        val roomDevice = deviceDatabaseDataSource.fetchRoomDevice(id) ?: return
        roomDevice.active = roomDevice.active.not()
        deviceDatabaseDataSource.replaceRoomDevice(roomDevice)
    }

    override suspend fun switchDevicesActive(userId:String, id: String) {
        var flag = false
        val devices = deviceDatabaseDataSource.fetchUserDevices(userId).filter {
            it.typeId == id
        }

        devices.forEach {device ->
            flag = device.active
        }

        devices.forEach {device ->
            device.active = !flag
            deviceDatabaseDataSource.replaceRoomDevice(device)
        }

    }

    override suspend fun fetchHomeDevices(userId:String): List<HomeDevicesDTO> {
        val roomDevices = deviceDatabaseDataSource.fetchHomeDevices(userId)
        val typeDevices = mutableMapOf<String,MutableList<RoomDeviceEntity>>()
        val homeDevices = mutableListOf<HomeDevicesDTO>()

        roomDevices.forEach {
            val tmp = typeDevices[it.typeId] ?: mutableListOf()
            tmp.add(it)
            typeDevices[it.typeId] = tmp
        }

        typeDevices.forEach {pair ->
            val device = deviceStaticStorageDataSource.fetchDevice(pair.key)
            var active = 0
            var inactive = 0
            pair.value.forEach {device ->
                if(device.active) active++
                else inactive++
            }
            homeDevices.add(HomeDevicesDTO(
                typeId = pair.key,
                name = device.name,
                active = active,
                inactive = inactive,
                icon = device.icon
            ))
        }

        return homeDevices
    }

}