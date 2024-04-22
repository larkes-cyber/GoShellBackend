package com.example.data.repository

import com.example.data.repository.mapper.toRoomDTO
import com.example.data.repository.mapper.toRoomEntity
import com.example.data.source.room.database.RoomDatabaseDataSource
import com.example.data.source.room.database.RoomDatabaseDataSourceImpl
import com.example.data.source.room.storage.RoomStaticStorageDataSource
import com.example.data.source.room.storage.RoomStaticStorageDataSourceImpl
import com.example.domain.model.RoomDTO
import com.example.domain.repository.RoomRepository

class RoomRepositoryImpl(
    private val roomDatabaseDataSource: RoomDatabaseDataSource,
    private val roomStaticStorageDataSource: RoomStaticStorageDataSource
):RoomRepository {
    override suspend fun createRoom(roomDTO: RoomDTO) {
        repeat(1000){
            roomDatabaseDataSource.insertRoom(roomDTO.toRoomEntity())
        }
    }

    override suspend fun fetchRooms(userId: String):List<RoomDTO> {
        return roomDatabaseDataSource.fetchRooms(userId).map { it.toRoomDTO() }
    }

    override suspend fun fetchRoomPhotos(): List<String> {
        return roomStaticStorageDataSource.fetchRoomPhotos()
    }
}