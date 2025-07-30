package com.example.hilalplaytest.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.EOFException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
/**
 * Base class for all repositories.
 *
 * It provides a way to build a suspend function that returns a [BaseResponse].
 * The suspend function is run on the IO dispatcher.
 * If the function throws an exception, it emits a [BaseResponse.Error] with the throwable and an error body.
 * If the exception is a [HttpException], it uses the http response code and body as the error body.
 * If the exception is a [SocketTimeoutException] or an [UnknownHostException], it uses a specific error body.
 * For all other exceptions, it uses the exception message as the error body.
 *
 * @param T the type of the data returned by the suspend function.
 */
open class BaseRepo {
    private val defaultDispatcher = Dispatchers.IO
    private val badInternet = 10

    /**
     * Builds a suspend function that returns a [BaseResponse].
     *
     * @param task the suspend function to build.
     * @return a flow that emits a [BaseResponse].
     */
    protected suspend fun <T : Any> buildTask(task: suspend () -> T) = flow<BaseResponse<T>> {
        emit(BaseResponse.Success(data = task()))
    }
        .flowOn(defaultDispatcher)
        .onStart {
            emit(BaseResponse.Loading)
        }
        .catch { throwable ->
            emit(BaseResponse.Error(throwable = throwable))
        }


    /**
     * Returns an error body based on the throwable.
     *
     * @param throwable the throwable to get the error body for.
     * @return an [BaseErrorServerResponse] with the error body.
     */
    private fun getErrorBody(throwable: Throwable): BaseErrorServerResponse {
        return when (throwable) {
            is EOFException, is UnknownHostException -> {
                BaseErrorServerResponse(badInternet, "No internet connection")
            }

            is SocketTimeoutException -> {
                BaseErrorServerResponse(badInternet, "No internet connection time out ")
            }

            is HttpException -> {
                BaseErrorServerResponse(
                    throwable.response()?.code() ?: 0,
                    throwable.response()?.errorBody()?.string() ?: throwable.message ?: ""
                )
            }

            else -> BaseErrorServerResponse(
                0,
                throwable.cause?.message ?: throwable.message ?: ""
            )

        }

    }

}