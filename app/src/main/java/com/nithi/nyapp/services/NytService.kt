package com.nithi.nyapp.services

import com.nithi.nyapp.model.NytResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NytService {
    @GET("svc/mostpopular/v2/viewed/1.json")
    suspend fun getNews(@Query("api-key")api_key:String):NytResponse
}