package com.example.domain.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ProfileDTO(
    val id:String = UUID.randomUUID().toString(),
    val name:String,
    val login:String,
    val password:String
)