package com.example.routes.profile.model

import kotlinx.serialization.Serializable

@Serializable
data class GetProfileRequest(
    val login:String
)