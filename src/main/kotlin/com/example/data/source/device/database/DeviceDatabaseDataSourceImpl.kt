package com.example.data.source.device.database

import com.example.data.source.device.model.RoomDeviceEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase

class DeviceDatabaseDataSourceImpl(
    private val coroutineDatabase: CoroutineDatabase
):DeviceDatabaseDataSource {

    private val db = coroutineDatabase.getCollection<RoomDeviceEntity>()

    override suspend fun insertRoomDevice(roomDeviceEntity: RoomDeviceEntity){
        db.insertOne(roomDeviceEntity)
    }

    override suspend fun fetchRoomDevices(roomId:String, login:String):List<RoomDeviceEntity>{
        val filter1 = Filters.eq("roomId", roomId)
        val filter2 = Filters.eq("login", login)
        return db.find(filter1, filter2).toList()
    }

    override suspend fun fetchRoomDevice(id:String):RoomDeviceEntity?{
        val filter = Filters.eq("id", id)
        return db.findOne(filter)
    }

    override suspend fun fetchHomeDevices(login:String):List<RoomDeviceEntity>{
        val filter = Filters.eq("login", login)
        return db.find(filter).toList()
    }

    override suspend fun replaceRoomDevice(roomDeviceEntity: RoomDeviceEntity){
        val filter = Filters.eq("id", roomDeviceEntity.id)
        db.deleteOne(filter)
        db.insertOne(roomDeviceEntity)
    }

    override suspend fun fetchUserDevices(login: String): List<RoomDeviceEntity> {
        val filter = Filters.eq("login", login)
        return db.find(filter).toList()
    }


}