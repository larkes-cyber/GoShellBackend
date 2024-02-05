package com.example.data.repository.mapper

import com.example.data.source.profile.model.ProfileEntity
import com.example.domain.model.ProfileDTO

fun ProfileDTO.toProfileEntity(): ProfileEntity {
    return ProfileEntity(
        id = id,
        name = name,
        login = login,
        password = password
    )
}

fun ProfileEntity.toProfileDTO():ProfileDTO{
    return ProfileDTO(
        id = id,
        name = name,
        login = login,
        password = password
    )
}