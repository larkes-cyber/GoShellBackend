package com.example.domain.repository

import com.example.domain.model.RoomDTO

interface RoomRepository {

    suspend fun createRoom(roomDTO: RoomDTO)
    suspend fun fetchRooms(userId:String):List<RoomDTO>
    suspend fun fetchRoomPhotos():List<String>

}