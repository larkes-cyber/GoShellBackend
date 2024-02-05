package com.example.data.source.room.database

import com.example.data.source.room.model.RoomEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase

class RoomDatabaseDataSourceImpl(
    private val coroutineDatabase: CoroutineDatabase
):RoomDatabaseDataSource {

    private val db = coroutineDatabase.getCollection<RoomEntity>()

    override suspend fun insertRoom(roomEntity: RoomEntity){
        db.insertOne(roomEntity)
    }

    override suspend fun fetchRooms(login:String):List<RoomEntity>{
        val filter = Filters.eq("login", login)
        return db.find(filter).toList()
    }

}