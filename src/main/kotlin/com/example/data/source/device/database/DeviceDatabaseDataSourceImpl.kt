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

    override suspend fun fetchRoomDevices(roomId:String, userId:String):List<RoomDeviceEntity>{
        val filter1 = Filters.eq("roomId", roomId)
        val filter2 = Filters.eq("userId", userId)
        println("${ db.find(filter1, filter2).toList()}  vcsdsdfffsdd")
        return db.find(filter1, filter2).toList()
    }

    override suspend fun fetchRoomDevice(id:String):RoomDeviceEntity?{
        val filter = Filters.eq("id", id)
        return db.findOne(filter)
    }

    override suspend fun fetchHomeDevices(id:String):List<RoomDeviceEntity>{
        println(id + " bvnvbnvbnbvbbbb")
        val filter = Filters.eq("userId", id)
        println("vbcffvbcvbcvbvb")
        println(db.find(filter).toList().toString() + " vbcffvbcvbcvbvb")
        return db.find(filter).toList()
    }

    override suspend fun replaceRoomDevice(roomDeviceEntity: RoomDeviceEntity){
        val filter = Filters.eq("id", roomDeviceEntity.id)
        db.deleteOne(filter)
        db.insertOne(roomDeviceEntity)
    }

    override suspend fun fetchUserDevices(id: String): List<RoomDeviceEntity> {
        val filter = Filters.eq("userId", id)
        return db.find(filter).toList()
    }


}