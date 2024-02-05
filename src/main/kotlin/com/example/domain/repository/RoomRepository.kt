package com.example.domain.repository

import com.example.domain.model.RoomDTO

interface RoomRepository {

    suspend fun createRoom(roomDTO: RoomDTO)
    suspend fun fetchRooms(login:String):List<RoomDTO>
    suspend fun fetchRoomPhotos():List<String>

}