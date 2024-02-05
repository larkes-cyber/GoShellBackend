package com.example.data.source.room.storage

import com.example.data.static_storage.room.RoomStaticStorage


class RoomStaticStorageDataSourceImpl(
    private val roomStaticStorage: RoomStaticStorage
):RoomStaticStorageDataSource {

    override fun fetchRoomPhotos() = roomStaticStorage.fetchPhotos()

}