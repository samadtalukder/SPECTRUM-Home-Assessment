package com.samad_talukder.spectrumassessment.data.api

sealed class ApiResult<out T : Any> {
    object Loading : ApiResult<Nothing>()
    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val errorMessage: String, val code: Int, ) : ApiResult<Nothing>()
}