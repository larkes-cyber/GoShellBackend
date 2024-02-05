package com.example.data.cache

import com.example.data.source.auth.model.TokenEntity


class TokenCacheStorageImpl():TokenCacheStorage {

    private val cache = mutableListOf<TokenEntity>()

    override fun cacheValue(token: TokenEntity){
        cache.add(token)
    }

    override fun fetchCache() = cache

}