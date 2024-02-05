package com.example.data.static_storage.device

import com.example.data.static_storage.device.model.DeviceStatic

class DeviceStaticStorage {

    private val storage = listOf(
        DeviceStatic(
            id = "0",
            name = "Smart TV",
            icon = ""
        ),
        DeviceStatic(
            id = "1",
            name = "Air Conditioner",
            icon = ""
        ),
        DeviceStatic(
            id = "2",
            name = "Air Purifier",
            icon = ""
        ),
        DeviceStatic(
            id = "3",
            name = "Smart Light 1",
            icon = ""
        ),
        DeviceStatic(
            id = "4",
            name = "Fan",
            icon = ""
        ),
        DeviceStatic(
            id = "5",
            name = "Fan",
            icon = "Smart Light 2"
        ),
    )

    fun fetchDevices() = storage
}