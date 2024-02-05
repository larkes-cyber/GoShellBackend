package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TokenDTO(
    val token:String,
    val login:String
)