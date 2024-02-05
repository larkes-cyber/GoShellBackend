package com.example.data.repository

import com.example.data.repository.mapper.toRoomDTO
import com.example.data.repository.mapper.toRoomEntity
import com.example.data.source.room.RoomDatabaseDataSource
import com.example.data.source.room.RoomStaticStorageDataSource
import com.example.domain.model.RoomDTO
import com.example.domain.repository.RoomRepository

class RoomRepositoryImpl(
    private val roomDatabaseDataSource: RoomDatabaseDataSource,
    private val roomStaticStorageDataSource: RoomStaticStorageDataSource
):RoomRepository {
    override suspend fun createRoom(roomDTO: RoomDTO) {
        roomDatabaseDataSource.insertRoom(roomDTO.toRoomEntity())
    }

    override suspend fun fetchRooms(login: String):List<RoomDTO> {
        return roomDatabaseDataSource.fetchRooms(login).map { it.toRoomDTO() }
    }

    override suspend fun fetchRoomPhotos(): List<String> {
        return roomStaticStorageDataSource.fetchRoomPhotos()
    }
}