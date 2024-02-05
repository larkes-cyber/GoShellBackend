package com.example.data.source.profile.model

import java.util.*

data class ProfileEntity(
    val id:String,
    var name:String,
    val login:String,
    val password:String
)