package com.example.data.repository.mapper

import com.example.data.source.device.model.RoomDeviceEntity
import com.example.data.static_storage.device.model.DeviceStatic
import com.example.domain.model.DeviceDTO
import com.example.domain.model.RoomDeviceDTO
import java.util.UUID

fun RoomDeviceDTO.toRoomDeviceEntity():RoomDeviceEntity{
    return RoomDeviceEntity(
        id = id ?: UUID.randomUUID().toString(),
        typeId = typeId,
        roomId = roomId,
        active = active,
        name = name!!,
        userId = userId
    )
}

fun DeviceStatic.toDeviceDTO():DeviceDTO{
    return DeviceDTO(
        id = id,
        name = name,
        icon = icon
    )
}