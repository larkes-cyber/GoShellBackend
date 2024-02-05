package com.example.data.source.device

import com.example.data.source.device.model.RoomDeviceEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne
import java.util.logging.Filter

class DeviceDatabaseDataSource(
    private val coroutineDatabase: CoroutineDatabase
) {

    private val db = coroutineDatabase.getCollection<RoomDeviceEntity>()

    suspend fun insertRoomDevice(roomDeviceEntity: RoomDeviceEntity){
        db.insertOne(roomDeviceEntity)
    }

    suspend fun fetchRoomDevices(roomId:String, login:String):List<RoomDeviceEntity>{
        val filter1 = Filters.eq("roomId", roomId)
        val filter2 = Filters.eq("login", login)
        return db.find(filter1, filter2).toList()
    }

    suspend fun fetchRoomDevice(id:String):RoomDeviceEntity?{
        val filter = Filters.eq("id", id)
        return db.findOne(filter)
    }

    suspend fun fetchHomeDevices(login:String):List<RoomDeviceEntity>{
        val filter = Filters.eq("login", login)
        return db.find(filter).toList()
    }

    suspend fun replaceRoomDevice(roomDeviceEntity: RoomDeviceEntity){
        val filter = Filters.eq("id", roomDeviceEntity.id)
        db.deleteOne(filter)
        db.insertOne(roomDeviceEntity)
    }



}