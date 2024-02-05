package com.example.data.cache

import com.example.data.source.auth.model.TokenEntity

interface TokenCacheStorage {
    fun cacheValue(token: TokenEntity)
    fun fetchCache():List<TokenEntity>
}