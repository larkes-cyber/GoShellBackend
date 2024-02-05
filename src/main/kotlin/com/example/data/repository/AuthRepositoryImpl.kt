package com.example.data.repository

import com.example.data.repository.mapper.toTokenDTO
import com.example.data.source.auth.AuthCacheDataSource
import com.example.data.source.auth.model.TokenEntity
import com.example.domain.model.ProfileDTO
import com.example.domain.model.TokenDTO
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.ProfileRepository
import com.example.utils.Resource

class AuthRepositoryImpl(
    private val authCacheDataSource: AuthCacheDataSource,
    private val profileRepository: ProfileRepository
):AuthRepository {
    override suspend fun performLogin(login: String, password: String): Resource<TokenDTO> {
        val tokens = authCacheDataSource.fetchToken(login)
        val passHash = AuthRepository.generatePasswordHash(password)
        if(tokens.isEmpty()) return Resource.Error("User doesn't exist")
        val token = tokens[0].toTokenDTO()
        if(AuthRepository.generateToken(login, password) == token.token) return Resource.Success(token)
        return Resource.Error("Incorrect password")
    }

    override suspend fun performRegistration(profileDTO:ProfileDTO): Resource<TokenDTO> {

        val tokens = authCacheDataSource.fetchToken(profileDTO.login)
        if(tokens.isEmpty()) return Resource.Error("User is already exists")

        val token = AuthRepository.generateToken(
            login = profileDTO.login,
            password = profileDTO.password
        )
        val userToken = TokenEntity(
            token = token,
            login = profileDTO.login
        )

        authCacheDataSource.cacheToken(userToken)
        profileRepository.createProfile(profileDTO)

        return Resource.Success(userToken.toTokenDTO())

    }

    override suspend fun checkToken(token: TokenDTO): Resource<Unit> {
        val tokens = authCacheDataSource.fetchToken(token.login)
        return if(tokens.isEmpty() || tokens[0].token != token.token)
            Resource.Error("Invalid Token")
        else Resource.Success(Unit)
    }
}