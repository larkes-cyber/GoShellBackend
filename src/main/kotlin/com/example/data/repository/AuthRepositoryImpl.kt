package com.example.data.repository

import com.example.data.repository.mapper.toTokenDTO
import com.example.data.source.auth.cache.AuthCacheDataSource
import com.example.data.source.auth.cache.AuthCacheDataSourceImpl
import com.example.data.source.auth.model.TokenEntity
import com.example.domain.model.ProfileDTO
import com.example.domain.model.TokenDTO
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.ProfileRepository
import com.example.security.token.JwtTokenService
import com.example.security.token.TokenClaim
import com.example.security.token.TokenConfigFactory
import com.example.utils.Resource
import java.util.*

class AuthRepositoryImpl(
    private val authCacheDataSource: AuthCacheDataSource,
    private val profileRepository: ProfileRepository
):AuthRepository {

    override suspend fun performLogin(login: String, password: String): Resource<TokenDTO> {
        println(authCacheDataSource.fetchTokens() + "  #############")
        val passCrypt = AuthRepository.generatePasswordHash(password)
        val user = profileRepository.fetchProfile(login)

        if(user.password != passCrypt) return Resource.Error("Wrong pass")

        val token = JwtTokenService.generate(
            config = TokenConfigFactory.make(),
            TokenClaim(
                name = "userId",
                value = user.id
            )
        )

        val userToken = TokenEntity(
            token = token,
            login = login
        )


        return Resource.Success(userToken.toTokenDTO())

    }

    override suspend fun performRegistration(profileDTO:ProfileDTO):Resource<String> {

        if(profileDTO.login.isEmpty() || profileDTO.password.isEmpty() || profileDTO.name.isEmpty()) return Resource.Error("field empty")

        val passCrypt = AuthRepository.generatePasswordHash(profileDTO.password)
        profileDTO.password = passCrypt

        val userId = UUID.randomUUID().toString()


        val token = JwtTokenService.generate(
            config = TokenConfigFactory.make(),
            TokenClaim(
                name = "userId",
                value = userId
            )
        )

        profileDTO.id = userId
        profileRepository.createProfile(profileDTO)

        return Resource.Success(token)
    }

    override suspend fun checkToken(token: TokenDTO): Resource<Unit> {
        val tokens = authCacheDataSource.fetchToken(token.login)
        println(tokens)
        return if(tokens.isEmpty() || tokens[0].token != token.token)
            Resource.Error("Invalid Token")
        else Resource.Success(Unit)
    }
}