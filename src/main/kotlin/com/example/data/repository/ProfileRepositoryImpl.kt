package com.example.data.repository

import com.example.data.repository.mapper.toProfileDTO
import com.example.data.repository.mapper.toProfileEntity
import com.example.data.source.profile.database.ProfileDatabaseDataSource
import com.example.data.source.profile.database.ProfileDatabaseDataSourceImpl
import com.example.domain.model.ProfileDTO
import com.example.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val profileDatabaseDataSource: ProfileDatabaseDataSource
):ProfileRepository {
    override suspend fun createProfile(profileDTO: ProfileDTO) {
        profileDatabaseDataSource.createProfile(profileDTO.toProfileEntity())
    }

    override suspend fun fetchProfile(login:String) = profileDatabaseDataSource.fetchProfile(login).toProfileDTO()
    override suspend fun fetchProfiles(): List<ProfileDTO> {
        return profileDatabaseDataSource.fetchProfiles().map { it.toProfileDTO() }
    }

    override suspend fun editProfile(profileDTO: ProfileDTO) {
        profileDatabaseDataSource.replaceProfile(profileDTO.toProfileEntity())
    }
}