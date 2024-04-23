package com.example.domain.repository

import com.example.domain.model.ProfileDTO

interface ProfileRepository {

    suspend fun createProfile(profileDTO: ProfileDTO)
    suspend fun fetchProfile(userId:String? = null, login:String? = null):ProfileDTO?
    suspend fun fetchProfiles():List<ProfileDTO>
    suspend fun editProfile(profileDTO: ProfileDTO)


}