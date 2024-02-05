package com.example.routes.auth.model

import kotlinx.serialization.Serializable

@Serializable
class AuthRequest(

    val login:String,
    val password:String

)