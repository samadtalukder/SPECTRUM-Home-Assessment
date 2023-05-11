package com.samad_talukder.spectrumassessment.data.api

sealed class ApiResult<out T> {

    object Loading : ApiResult<Nothing>()

    data class Success<out T>(
        val data: T
    ) : ApiResult<T>()

    data class Error(
        val errorMessage: String,
        val code: Int,
    ) : ApiResult<Nothing>()
}