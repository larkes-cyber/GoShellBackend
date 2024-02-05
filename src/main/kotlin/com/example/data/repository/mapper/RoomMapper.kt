package com.example.data.repository.mapper

import com.example.data.source.room.model.RoomEntity
import com.example.domain.model.RoomDTO
import java.util.UUID

fun RoomDTO.toRoomEntity():RoomEntity{
    return RoomEntity(
        id = id ?: UUID.randomUUID().toString(),
        login = login,
        name = name,
        image = image
    )
}

fun RoomEntity.toRoomDTO():RoomDTO{
    return RoomDTO(
        id = id,
        login = login,
        name = name,
        image = image
    )
}