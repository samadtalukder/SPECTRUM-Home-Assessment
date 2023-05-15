package com.samad_talukder.spectrumassessment.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samad_talukder.spectrumassessment.data.api.ApiResult
import com.samad_talukder.spectrumassessment.domain.model.MovieResponse
import com.samad_talukder.spectrumassessment.domain.usecase.MovieByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieByCategoryViewModel @Inject constructor(
    application: Application,
    private val movieByCategoryUseCase: MovieByCategoryUseCase,
) : AndroidViewModel(application) {

    private val _movieCategoryResponse: MutableLiveData<ApiResult<MovieResponse>> =
        MutableLiveData()
    val movieCategoryResponse: MutableLiveData<ApiResult<MovieResponse>> =
        _movieCategoryResponse


    fun getMovieByCategory(
        movieCategory: String,
        page: Int
    ) = viewModelScope.launch {
        movieByCategoryUseCase.execute(movieCategory, page).collect { values ->
            _movieCategoryResponse.value = values
        }
    }
}

