package com.nithi.nyapp

import com.nithi.nyapp.services.NytService
import com.nithi.nyapp.services.RemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Retrofit

class RetrofitClientTest{
    @Test
    fun  testRetrofitInstance()= runBlocking{
        val client=RemoteDataSource().buildApi(NytService::class.java)
        val response=client.getNews("8enmothO72L3thlJBMyWBJPTRdGOkhGi")
        assert(response.status=="OK")
    }
}