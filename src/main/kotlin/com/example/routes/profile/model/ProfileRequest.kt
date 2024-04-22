package com.example.routes.profile.model

import com.example.domain.model.ProfileDTO
import com.example.domain.model.TokenDTO
import kotlinx.serialization.Serializable

@Serializable
data class ProfileRequest(
    val profile:ProfileDTO
)