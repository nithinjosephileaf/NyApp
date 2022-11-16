package com.nithi.nyapp.services

import ResponseState
import com.nithi.nyapp.model.NytResponse
import com.nithi.nyapp.services.BaseApiResponse
import com.nithi.nyapp.services.NytService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor( val service: NytService): BaseApiResponse() {

    suspend fun getNews(): Flow<ResponseState<NytResponse>> {
        return  flow<ResponseState<NytResponse>> {
            emit(ResponseState.Loading("Loading"))
            emit(safeApi { service.getNews("8enmothO72L3thlJBMyWBJPTRdGOkhGi") })
        }.flowOn(Dispatchers.IO)
    }
}
