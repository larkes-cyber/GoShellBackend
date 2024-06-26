package com.example.data.static_storage.device

import com.example.data.static_storage.device.model.DeviceStatic
import com.example.utils.Constants.SERVER_API

class DeviceStaticStorageImpl:DeviceStaticStorage {

    private val storage = listOf(
        DeviceStatic(
            id = "0",
            name = "Smart TV",
            icon = "http://$SERVER_API:8080/device/icon?name=smart_tv"
        ),
        DeviceStatic(
            id = "1",
            name = "Air Conditioner",
            icon = "http://$SERVER_API:8080/device/icon?name=air_conditioner"
        ),
        DeviceStatic(
            id = "2",
            name = "Air Purifier",
            icon = "http://$SERVER_API:8080/device/icon?name=air_purifier"
        ),
        DeviceStatic(
            id = "3",
            name = "Smart Light 1",
            icon = "http://$SERVER_API:8080/device/icon?name=smart_light"
        ),
        DeviceStatic(
            id = "4",
            name = "Fan",
            icon = "http://$SERVER_API:8080/device/icon?name=fan"
        )
    )

    override fun fetchDevices() = storage
}