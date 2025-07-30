package com.example.hilalplaytest.core

sealed class BaseResponse<out T> {
    data object Loading : BaseResponse<Nothing>()
    data class Error(val throwable: Throwable) : BaseResponse<Nothing>()
    data class Success<T : Any>(val data: T) : BaseResponse<T>()
}
