package com.example.data.source.auth.cache

import com.example.data.cache.TokenCacheStorage
import com.example.data.cache.TokenCacheStorageImpl
import com.example.data.source.auth.model.TokenEntity

class AuthCacheDataSourceImpl(
    private val cache: TokenCacheStorage
):AuthCacheDataSource {

    override fun cacheToken(token:TokenEntity){
        cache.cacheValue(token)
    }

    override fun fetchToken(login:String):List<TokenEntity> = cache.fetchCache().filter { it.login == login }

    override fun fetchTokens() = cache.fetchCache()

}