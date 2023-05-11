package com.samad_talukder.spectrumassessment.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samad_talukder.spectrumassessment.data.api.ApiResult
import com.samad_talukder.spectrumassessment.domain.model.MovieResponse
import com.samad_talukder.spectrumassessment.domain.usecase.MovieSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    application: Application,
    private val movieSearchUseCase: MovieSearchUseCase,
) : AndroidViewModel(application) {

    private val _movieSearchResponse: MutableLiveData<ApiResult<MovieResponse>> =
        MutableLiveData()
    val movieSearchResponse: LiveData<ApiResult<MovieResponse>> =
        _movieSearchResponse


    fun getMovieByCategory(
        query: String,
        page: Int
    ) = viewModelScope.launch {
        _movieSearchResponse.value = ApiResult.Loading

        movieSearchUseCase.execute(query, page).collect { values ->
            _movieSearchResponse.value = values
        }
    }
}

