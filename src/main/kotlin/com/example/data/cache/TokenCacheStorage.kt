package com.example.data.cache

import com.example.data.source.auth.model.TokenEntity


class TokenCacheStorage() {

    private val cache = mutableListOf<TokenEntity>()

    fun cacheValue(token: TokenEntity){
        cache.add(token)
    }

    fun getCache() = cache

}