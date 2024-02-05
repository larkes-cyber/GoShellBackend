package com.example.data.source.room.database

import com.example.data.source.room.model.RoomEntity

interface RoomDatabaseDataSource {

    suspend fun insertRoom(roomEntity: RoomEntity)
    suspend fun fetchRooms(login:String):List<RoomEntity>

}