package com.example.data.source.profile

import com.example.data.source.profile.model.ProfileEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne

class ProfileDatabaseDataSource(
    private val coroutineDatabase: CoroutineDatabase
) {

    private val db = coroutineDatabase.getCollection<ProfileEntity>()

    suspend fun createProfile(profileEntity: ProfileEntity){
        db.insertOne(profileEntity)
    }

    suspend fun fetchProfile(login:String): ProfileEntity {

        val filter = Filters.eq("login", login)
        return db.findOne(filter)!!

    }

    suspend fun fetchProfiles():List<ProfileEntity>{
        return db.find().toList()
    }

    suspend fun replaceProfile(profileEntity: ProfileEntity){
        val filter = Filters.eq("login", profileEntity.login)
        val profile = db.findOne(filter)
        db.deleteOne(filter)
        profile!!.name = profileEntity.name
        db.insertOne(profile)
    }




}