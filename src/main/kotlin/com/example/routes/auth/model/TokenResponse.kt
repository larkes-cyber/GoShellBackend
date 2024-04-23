package com.example.routes.auth.model

import kotlinx.serialization.Serializable

@Serializable
class TokenResponse(
    val token:String
)