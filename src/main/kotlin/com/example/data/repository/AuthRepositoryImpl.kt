package com.example.data.repository

import com.example.data.repository.mapper.toTokenDTO
import com.example.data.source.auth.AuthCacheDataSource
import com.example.data.source.auth.model.TokenEntity
import com.example.domain.model.ProfileDTO
import com.example.domain.model.TokenDTO
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.ProfileRepository
import com.example.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthRepositoryImpl(
    private val authCacheDataSource: AuthCacheDataSource,
    private val profileRepository: ProfileRepository
):AuthRepository {
    private suspend fun pumpTokens(){
        profileRepository
            .fetchProfiles()
            .forEach {
                val token = AuthRepository.generateToken(password = it.password, login = it.login)
                authCacheDataSource.cacheToken(
                    TokenEntity(
                    token = token,
                    login = it.login
                  )
                )
            }
    }

    override suspend fun performLogin(login: String, password: String): Resource<TokenDTO> {
        if(authCacheDataSource.fetchTokens().isEmpty()) pumpTokens()

        val tokens = authCacheDataSource.fetchToken(login)
        val passCrypt = AuthRepository.generatePasswordHash(password)
        if(tokens.isEmpty()) return Resource.Error("User doesn't exist")

        val token = tokens[0].toTokenDTO()
        if(AuthRepository.generateToken(login = login, password = passCrypt) == token.token) return Resource.Success(token)

        return Resource.Error("Incorrect password")
    }

    override suspend fun performRegistration(profileDTO:ProfileDTO): Resource<TokenDTO> {

        val tokens = authCacheDataSource.fetchToken(profileDTO.login)
        if(tokens.isNotEmpty()) return Resource.Error("User is already exists")

        val passCrypt = AuthRepository.generatePasswordHash(profileDTO.password)
        profileDTO.password = passCrypt

        val token = AuthRepository.generateToken(
            login = profileDTO.login,
            password = passCrypt
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