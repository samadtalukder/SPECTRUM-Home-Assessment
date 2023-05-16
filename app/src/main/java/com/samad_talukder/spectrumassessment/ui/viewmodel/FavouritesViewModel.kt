package com.samad_talukder.spectrumassessment.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samad_talukder.spectrumassessment.data.api.ApiResult
import com.samad_talukder.spectrumassessment.data.local.entity.Favorite
import com.samad_talukder.spectrumassessment.domain.usecase.AddFavouriteUseCase
import com.samad_talukder.spectrumassessment.domain.usecase.IsFavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    application: Application,
    private val addFavouriteUseCase: AddFavouriteUseCase,
    private val isFavouriteUseCase: IsFavouriteUseCase,
) : AndroidViewModel(application) {

    private val _addFavouriteDB: MutableLiveData<ApiResult<Long>> =
        MutableLiveData()
    val addFavouriteDB: MutableLiveData<ApiResult<Long>> =
        _addFavouriteDB

    private val _isFavoriteState = MutableLiveData<Boolean>()
    val isFavoriteState: MutableLiveData<Boolean> = _isFavoriteState

    fun addFavourite(favorite: Favorite) = viewModelScope.launch {
        val result = addFavouriteUseCase.execute(favorite)
        _addFavouriteDB.value = result
    }

    fun isFavourite(movieID: Int) = viewModelScope.launch {
        isFavouriteUseCase.execute(movieID).collect { values ->
            _isFavoriteState.value = values
        }
    }

}

