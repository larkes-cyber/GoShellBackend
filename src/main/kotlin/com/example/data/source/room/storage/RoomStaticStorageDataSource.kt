package com.example.data.source.room.storage

interface RoomStaticStorageDataSource {
    fun fetchRoomPhotos():List<String>
}