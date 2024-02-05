package com.example.data.cache

object TokenCacheStorageFactory {

    private val instance = TokenCacheStorageImpl()
    fun makeStorage():TokenCacheStorage = instance
}