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

    override suspend fun fetchRooms(userId:String):List<RoomEntity>{
        val filter = Filters.eq("userId", userId)
        return db.find(filter).toList()
    }

}