package com.nithi.nyapp.services

import com.nithi.nyapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {
    private val  loggingInterceptor=HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val client=OkHttpClient.Builder().apply {

        if (BuildConfig.DEBUG){
            this.addInterceptor(loggingInterceptor)
        }

        this.connectTimeout(1,TimeUnit.MINUTES)
        this.readTimeout(1,TimeUnit.MINUTES)
        this.writeTimeout(1,TimeUnit.MINUTES)
    }.build()

    fun<API>buildApi(api: Class<API>):API{
        return Retrofit.Builder().client(client).addConverterFactory(GsonConverterFactory.create()).baseUrl(
            BuildConfig.BASE_URL
        ).build().create(api)
    }
}