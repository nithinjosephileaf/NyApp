package com.nithi.nyapp.services

import ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException

abstract class BaseApiResponse {
    suspend fun <T> safeApi(apiCall: suspend () -> T): ResponseState<T> =
        withContext(Dispatchers.IO) {
            try {
                ResponseState.Success(apiCall.invoke())
            } catch (t: Throwable) {
                when (t) {

                    is SocketTimeoutException -> ResponseState.Failure(false, 408, null,"time out")
                    is HttpException -> {
                        ResponseState.Failure(false, t.code(),t.response()?.errorBody(),t.message())
                    }
                    else -> {
                        ResponseState.Failure(true, null, null,"no network")
                    }
                }
            }
        }
}