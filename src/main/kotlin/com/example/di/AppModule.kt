package com.example.di

import com.example.data.cache.TokenCacheStorage
import com.example.data.cache.TokenCacheStorageFactory
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.DeviceRepositoryImpl
import com.example.data.repository.RoomRepositoryImpl
import com.example.data.source.auth.AuthCacheDataSource
import com.example.data.source.device.DeviceDatabaseDataSource
import com.example.data.source.device.DeviceStaticStorageDataSource
import com.example.data.source.profile.ProfileDatabaseDataSource
import com.example.data.source.room.RoomDatabaseDataSource
import com.example.data.source.room.RoomStaticStorageDataSource
import com.example.data.static_storage.device.DeviceStaticStorage
import com.example.data.static_storage.device.DeviceStaticStorageFactory
import com.example.data.static_storage.room.RoomStaticStorage
import com.example.data.static_storage.room.RoomStaticStorageFactory
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.DeviceRepository
import com.example.domain.repository.RoomRepository
import org.koin.dsl.module

val appModule = module {

    single<DeviceStaticStorage> { DeviceStaticStorageFactory.makeStorage() }
    single<RoomStaticStorage> { RoomStaticStorageFactory.makeStaticStorage() }
    single<TokenCacheStorage> { TokenCacheStorageFactory.makeStorage() }
    single<AuthCacheDataSource> { AuthCacheDataSource(get()) }
    single<DeviceDatabaseDataSource> { DeviceDatabaseDataSource(get()) }
    single<DeviceStaticStorageDataSource> { DeviceStaticStorageDataSource(get()) }
    single<ProfileDatabaseDataSource> { ProfileDatabaseDataSource(get()) }
    single<RoomDatabaseDataSource> { RoomDatabaseDataSource(get()) }
    single<RoomStaticStorageDataSource> { RoomStaticStorageDataSource(get()) }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<DeviceRepository> { DeviceRepositoryImpl(get(), get()) }
    single<RoomRepository> { RoomRepositoryImpl(get(), get()) }
    single<RoomRepository> { RoomRepositoryImpl(get(), get()) }

}