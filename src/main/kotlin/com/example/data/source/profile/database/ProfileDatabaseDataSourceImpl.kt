package com.example.data.source.profile.database

import com.example.data.source.profile.model.ProfileEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase

class ProfileDatabaseDataSourceImpl(
    private val coroutineDatabase: CoroutineDatabase
):ProfileDatabaseDataSource {

    private val db = coroutineDatabase.getCollection<ProfileEntity>()

    override suspend fun createProfile(profileEntity: ProfileEntity){
        db.insertOne(profileEntity)
    }

    override suspend fun fetchProfile(login:String): ProfileEntity {

        val filter = Filters.eq("login", login)
        return db.findOne(filter)!!

    }

    override suspend fun fetchProfiles():List<ProfileEntity>{
        return db.find().toList()
    }

    override suspend fun replaceProfile(profileEntity: ProfileEntity){
        val filter = Filters.eq("login", profileEntity.login)
        val profile = db.findOne(filter)
        db.deleteOne(filter)
        profile!!.name = profileEntity.name
        db.insertOne(profile)
    }




}