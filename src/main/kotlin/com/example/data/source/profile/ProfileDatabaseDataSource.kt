package com.example.data.source.profile

import com.example.data.source.profile.model.ProfileEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase

class ProfileDatabaseDataSource(
    private val coroutineDatabase: CoroutineDatabase
) {

    private val db = coroutineDatabase.getCollection<ProfileEntity>()

    suspend fun createProfile(profileEntity: ProfileEntity){
        db.insertOne(profileEntity)
    }

    suspend fun fetchUser(login:String): ProfileEntity {

        val filter = Filters.eq("login", login)
        return db.findOne(filter)!!

    }


}