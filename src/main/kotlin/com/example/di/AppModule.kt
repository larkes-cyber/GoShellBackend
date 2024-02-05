package com.example.di

import com.example.data.cache.TokenCacheStorage
import com.example.data.cache.TokenCacheStorageFactory
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.DeviceRepositoryImpl
import com.example.data.repository.ProfileRepositoryImpl
import com.example.data.repository.RoomRepositoryImpl
import com.example.data.source.auth.cache.AuthCacheDataSource
import com.example.data.source.auth.cache.AuthCacheDataSourceImpl
import com.example.data.source.device.database.DeviceDatabaseDataSource
import com.example.data.source.device.database.DeviceDatabaseDataSourceImpl
import com.example.data.source.device.storage.DeviceStaticStorageDataSource
import com.example.data.source.device.storage.DeviceStaticStorageDataSourceImpl
import com.example.data.source.profile.database.ProfileDatabaseDataSource
import com.example.data.source.profile.database.ProfileDatabaseDataSourceImpl
import com.example.data.source.room.database.RoomDatabaseDataSource
import com.example.data.source.room.database.RoomDatabaseDataSourceImpl
import com.example.data.source.room.storage.RoomStaticStorageDataSource
import com.example.data.source.room.storage.RoomStaticStorageDataSourceImpl
import com.example.data.static_storage.device.DeviceStaticStorage
import com.example.data.static_storage.device.DeviceStaticStorageFactory
import com.example.data.static_storage.room.RoomStaticStorage
import com.example.data.static_storage.room.RoomStaticStorageFactory
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.DeviceRepository
import com.example.domain.repository.ProfileRepository
import com.example.domain.repository.RoomRepository
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val appModule = module {

    single {
        KMongo.createClient()
            .coroutine
            .getDatabase("go_shell_db")
    }

    single { DeviceStaticStorageFactory.makeStorage() }
    single<RoomStaticStorage> { RoomStaticStorageFactory.makeStaticStorage() }
    single { TokenCacheStorageFactory.makeStorage() }
    single<AuthCacheDataSource> { AuthCacheDataSourceImpl(get()) }
    single<DeviceDatabaseDataSource> { DeviceDatabaseDataSourceImpl(get()) }
    single<DeviceStaticStorageDataSource> { DeviceStaticStorageDataSourceImpl(get()) }
    single<ProfileDatabaseDataSource> { ProfileDatabaseDataSourceImpl(get()) }
    single<RoomDatabaseDataSource> { RoomDatabaseDataSourceImpl(get()) }
    single<RoomStaticStorageDataSource> { RoomStaticStorageDataSourceImpl(get()) }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<DeviceRepository> { DeviceRepositoryImpl(get(), get()) }
    single<ProfileRepository> { ProfileRepositoryImpl(get()) }
    single<RoomRepository> { RoomRepositoryImpl(get(), get()) }

}