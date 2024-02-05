package com.example.data.source.device.storage

import com.example.data.static_storage.device.DeviceStaticStorage
import com.example.data.static_storage.device.DeviceStaticStorageImpl

class DeviceStaticStorageDataSourceImpl(
    private val deviceStaticStorage: DeviceStaticStorage
):DeviceStaticStorageDataSource {
    override fun fetchDevices() = deviceStaticStorage.fetchDevices()
    override fun fetchDevice(id:String) = fetchDevices().find { it.id == id }!!
}