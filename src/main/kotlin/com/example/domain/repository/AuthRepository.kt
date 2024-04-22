package com.example.domain.repository

import com.auth0.jwt.JWT
import com.auth0.jwt.RegisteredClaims.ISSUER
import com.auth0.jwt.RegisteredClaims.SUBJECT
import com.auth0.jwt.algorithms.Algorithm
import com.example.domain.model.ProfileDTO
import com.example.domain.model.TokenDTO
import com.example.utils.Resource
import io.ktor.util.*

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

interface AuthRepository {

    suspend fun performLogin(login:String, password:String):Resource<TokenDTO>
    suspend fun performRegistration(profileDTO: ProfileDTO):Resource<String>
    suspend fun checkToken(token:TokenDTO):Resource<Unit>

    companion object{
        private const val JWT_SECRET_KEY = "JWT_SECRET"
        private const val HASH_SECRET_KEY = "JWT_SECRET"
        private val HMACKEY = SecretKeySpec(HASH_SECRET_KEY.toByteArray(), "HmacSHA1")


        private val algorithm = Algorithm.HMAC512(JWT_SECRET_KEY)
        private val verifier = JWT
            .require(algorithm)
            .withIssuer(ISSUER)
            .build()

        fun generateToken(password:String, login:String): String {
            return JWT.create()
                .withSubject(SUBJECT)
                .withIssuer(ISSUER)
                .withClaim("email", login)
                .withClaim("password", password)
                .sign(algorithm)
        }

        fun generatePasswordHash(password: String): String {
            val hmac = Mac.getInstance("HmacSHA1")
            hmac.init(HMACKEY)
            return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
        }


    }

}