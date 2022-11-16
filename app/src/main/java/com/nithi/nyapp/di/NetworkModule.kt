package com.nithi.nyapp.di

import com.nithi.nyapp.services.NytService
import com.nithi.nyapp.services.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideService(remoteDataSource: RemoteDataSource):NytService{
        return remoteDataSource.buildApi(NytService::class.java)
    }
}