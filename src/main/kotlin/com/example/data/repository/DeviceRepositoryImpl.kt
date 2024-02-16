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
    override suspend fun insertRoomDevice(roomDeviceDTO: RoomDeviceDTO) {
        if(roomDeviceDTO.name == null){
            val device = deviceStaticStorageDataSource.fetchDevice(roomDeviceDTO.typeId)
            roomDeviceDTO.name = device.name
        }
        deviceDatabaseDataSource.insertRoomDevice(roomDeviceDTO.toRoomDeviceEntity())
    }

    override suspend fun fetchDevices(): List<DeviceDTO> {
        return deviceStaticStorageDataSource.fetchDevices().map { it.toDeviceDTO() }
    }

    override suspend fun fetchRoomDevices(roomId: String, login:String): List<RoomDeviceDTO> {
        return deviceDatabaseDataSource.fetchRoomDevices(
            roomId = roomId,
            login = login
        ).map {
            val device = deviceStaticStorageDataSource.fetchDevice(it.typeId)
            RoomDeviceDTO(
                id = it.id,
                typeId = it.typeId,
                roomId = it.roomId,
                active = it.active,
                icon = device.icon,
                name = it.name,
                login = it.login
            )
        }
    }

    override suspend fun switchDeviceActive(id: String) {
        val roomDevice = deviceDatabaseDataSource.fetchRoomDevice(id) ?: return
        roomDevice.active = roomDevice.active.not()
        deviceDatabaseDataSource.replaceRoomDevice(roomDevice)
    }

    override suspend fun switchDevicesActive(login:String, id: String) {
        var flag = false
        val devices = deviceDatabaseDataSource.fetchUserDevices(login).filter {
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

    override suspend fun fetchHomeDevices(login:String): List<HomeDevicesDTO> {
        val roomDevices = deviceDatabaseDataSource.fetchHomeDevices(login)
        val typeDevices = mutableMapOf<String,MutableList<RoomDeviceEntity>>()
        val homeDevices = mutableListOf<HomeDevicesDTO>()

        roomDevices.forEach {
            val tmp = typeDevices[it.typeId] ?: mutableListOf()
            tmp.add(it)
            typeDevices[it.typeId] = tmp
        }

        typeDevices.forEach {pair ->
            val name = deviceStaticStorageDataSource.fetchDevice(pair.key).name
            var active = 0
            var inactive = 0
            pair.value.forEach {device ->
                if(device.active) active++
                else inactive++
            }
            homeDevices.add(HomeDevicesDTO(
                typeId = pair.key,
                name = name,
                active = active,
                inactive = inactive
            ))
        }

        return homeDevices
    }

}