package app.yoda.huddl.huddlutils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

open class HuddleBaseRepository {

    suspend fun <T> huddleSafeApiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO, endpointName : String? = "", apiCall : suspend () -> T) : ResultWrapper<T> {
        return withContext(dispatcher){
            try {
                ResultWrapper.Success(apiCall.invoke())
            } catch (throwable : Throwable){
                when (throwable) {
                    is IOException -> ResultWrapper.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = convertErrorBody(throwable)
                        ResultWrapper.GenericError(code, errorResponse)
                    }
                    else -> {
                        ResultWrapper.GenericError(null, null)
                    }
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): GenericSuccessFailResponse {
        return GenericSuccessFailResponse(
                false,
                "Constants.ERR_TECH_ISSUE"
            )
    }

//    suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO, endpointName : String?= "unknown", apiCall: suspend () -> T): ResultWrapper<T> {
//        return withContext(dispatcher) {
//            try {
//                ResultWrapper.Success(apiCall.invoke())
//            } catch (throwable: Throwable) {

            }