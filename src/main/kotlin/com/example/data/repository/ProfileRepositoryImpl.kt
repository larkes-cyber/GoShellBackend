package com.example.data.repository

import com.example.data.repository.mapper.toProfileDTO
import com.example.data.repository.mapper.toProfileEntity
import com.example.data.source.profile.database.ProfileDatabaseDataSource
import com.example.data.source.profile.database.ProfileDatabaseDataSourceImpl
import com.example.data.source.profile.model.ProfileEntity
import com.example.domain.model.ProfileDTO
import com.example.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val profileDatabaseDataSource: ProfileDatabaseDataSource
):ProfileRepository {
    override suspend fun createProfile(profileDTO: ProfileDTO) {
        profileDatabaseDataSource.createProfile(profileDTO.toProfileEntity())
    }

    override suspend fun fetchProfile(userId:String?, login:String?):ProfileDTO?{

        return profileDatabaseDataSource.fetchProfile(id = userId, login = login)?.toProfileDTO()
    }
    override suspend fun fetchProfiles(): List<ProfileDTO> {
        return profileDatabaseDataSource.fetchProfiles().map { it.toProfileDTO() }
    }

    override suspend fun editProfile(profileDTO: ProfileDTO) {
        profileDatabaseDataSource.replaceProfile(profileDTO.toProfileEntity())
    }
}