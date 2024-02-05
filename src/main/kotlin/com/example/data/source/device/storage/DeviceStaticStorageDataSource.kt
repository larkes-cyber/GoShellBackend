package com.example.data.source.device.storage

import com.example.data.static_storage.device.model.DeviceStatic

interface DeviceStaticStorageDataSource {
    fun fetchDevices():List<DeviceStatic>
    fun fetchDevice(id:String): DeviceStatic

}