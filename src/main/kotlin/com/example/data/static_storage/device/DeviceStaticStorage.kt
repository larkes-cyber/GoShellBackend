package com.example.data.static_storage.device

import com.example.data.static_storage.device.model.DeviceStatic

interface DeviceStaticStorage {
    fun fetchDevices():List<DeviceStatic>
}