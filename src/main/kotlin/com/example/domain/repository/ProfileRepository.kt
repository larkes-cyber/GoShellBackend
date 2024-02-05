package com.example.domain.repository

import com.example.domain.model.ProfileDTO

interface ProfileRepository {

    suspend fun createProfile(profileDTO: ProfileDTO)
    suspend fun fetchProfile(login:String):ProfileDTO


}