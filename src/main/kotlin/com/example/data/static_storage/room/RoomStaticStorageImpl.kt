package com.example.data.static_storage.room

class RoomStaticStorageImpl:RoomStaticStorage {

    private val photos = listOf(
        ""
    )
    override fun fetchPhotos() = photos
}