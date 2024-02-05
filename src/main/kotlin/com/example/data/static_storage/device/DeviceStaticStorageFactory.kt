package com.example.data.static_storage.device

object DeviceStaticStorageFactory {

    private val instance = DeviceStaticStorageImpl()
    fun makeStorage():DeviceStaticStorage = instance
}