package com.example.security.token

object TokenConfigFactory {

    private val tokenConfig = TokenConfig(
        issuer = "jwt.issuer",
        audience = "jwt.audience",
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
        secret = "very secret key"
    )

    fun make() = tokenConfig

}