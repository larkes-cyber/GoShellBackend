package com.example.data.source.room

import com.example.data.static_storage.room.RoomStaticStorage

class RoomStaticStorageDataSource(
    private val roomStaticStorage: RoomStaticStorage
) {

    fun fetchRoomPhotos() = roomStaticStorage.fetchPhotos()

}