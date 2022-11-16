
import okhttp3.ResponseBody

sealed class ResponseState<out T> {
    data class Loading<T>(val message: String) : ResponseState<T>()
    data class Success<out T>(val result: T) : ResponseState<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
        val message: String
    ) : ResponseState<Nothing>()
}