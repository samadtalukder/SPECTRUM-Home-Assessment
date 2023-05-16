package com.samad_talukder.spectrumassessment.data.repository

import com.samad_talukder.spectrumassessment.data.local.entity.Favorite
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    suspend fun addFavourite(favorite: Favorite): Long
    suspend fun isFavourite(movieId: Int): Flow<Boolean>

}