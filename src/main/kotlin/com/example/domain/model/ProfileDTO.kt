package com.example.domain.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ProfileDTO(
    var id:String = UUID.randomUUID().toString(),
    val name:String,
    val login:String,
    var password:String
)