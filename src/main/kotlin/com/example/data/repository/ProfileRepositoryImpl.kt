package com.example.data.repository

import com.example.data.repository.mapper.toProfileDTO
import com.example.data.repository.mapper.toProfileEntity
import com.example.data.source.profile.ProfileDatabaseDataSource
import com.example.domain.model.ProfileDTO
import com.example.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val profileDatabaseDataSource: ProfileDatabaseDataSource
):ProfileRepository {
    override suspend fun createProfile(profileDTO: ProfileDTO) {
        profileDatabaseDataSource.createProfile(profileDTO.toProfileEntity())
    }

    override suspend fun fetchProfile(login:String) = profileDatabaseDataSource.fetchUser(login).toProfileDTO()
}