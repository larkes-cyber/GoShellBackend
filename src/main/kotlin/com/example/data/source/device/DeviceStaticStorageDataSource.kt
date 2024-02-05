package com.example.data.source.device

import com.example.data.static_storage.device.DeviceStaticStorage

class DeviceStaticStorageDataSource(
    private val deviceStaticStorage: DeviceStaticStorage
) {
    fun fetchDevices() = deviceStaticStorage.fetchDevices()
    fun fetchDevice(id:String) = fetchDevices().find { it.id == id }!!
}