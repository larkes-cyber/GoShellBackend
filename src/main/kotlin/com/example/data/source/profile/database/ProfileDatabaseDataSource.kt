package com.example.data.source.profile.database

import com.example.data.source.profile.model.ProfileEntity

interface ProfileDatabaseDataSource {
    suspend fun createProfile(profileEntity: ProfileEntity)
    suspend fun fetchProfile(login:String):ProfileEntity
    suspend fun fetchProfiles():List<ProfileEntity>
    suspend fun replaceProfile(profileEntity: ProfileEntity)
}