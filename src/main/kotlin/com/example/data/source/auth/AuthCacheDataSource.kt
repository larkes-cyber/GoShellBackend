package com.example.data.source.auth

import com.example.data.cache.TokenCacheStorage
import com.example.data.source.auth.model.TokenEntity

class AuthCacheDataSource(
    private val cache:TokenCacheStorage
) {

    fun cacheToken(token:TokenEntity){
        cache.cacheValue(token)
    }

    fun fetchToken(login:String):List<TokenEntity> = cache.getCache().filter { it.login == login }

    fun fetchTokens() = cache.getCache()

}