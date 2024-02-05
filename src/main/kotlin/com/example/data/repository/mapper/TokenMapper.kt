package com.example.data.repository.mapper

import com.example.data.source.auth.model.TokenEntity
import com.example.domain.model.TokenDTO

fun TokenEntity.toTokenDTO():TokenDTO{
    return TokenDTO(
        login = login,
        token = token
    )
}