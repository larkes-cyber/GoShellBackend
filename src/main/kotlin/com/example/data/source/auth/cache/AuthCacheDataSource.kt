package com.example.data.source.auth.cache

import com.example.data.source.auth.model.TokenEntity

interface AuthCacheDataSource {

    fun cacheToken(token: TokenEntity)
    fun fetchToken(login:String):List<TokenEntity>
    fun fetchTokens():List<TokenEntity>


}