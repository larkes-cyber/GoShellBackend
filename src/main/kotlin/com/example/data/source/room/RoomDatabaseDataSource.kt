package com.example.data.source.room

import com.example.data.source.room.model.RoomEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne
import java.util.logging.Filter

class RoomDatabaseDataSource(
    private val coroutineDatabase: CoroutineDatabase
) {

    private val db = coroutineDatabase.getCollection<RoomEntity>()

    suspend fun insertRoom(roomEntity: RoomEntity){
        db.insertOne(roomEntity)
    }

    suspend fun fetchRooms(login:String):List<RoomEntity>{
        val filter = Filters.eq("login", login)
        return db.find(filter).toList()
    }

}