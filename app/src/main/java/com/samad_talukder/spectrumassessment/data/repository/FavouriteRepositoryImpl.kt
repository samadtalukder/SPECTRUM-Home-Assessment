package com.samad_talukder.spectrumassessment.data.repository

import com.samad_talukder.spectrumassessment.data.local.dao.FavouritesDao
import com.samad_talukder.spectrumassessment.data.local.entity.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(private val favouriteDatabase: FavouritesDao) :
    FavouriteRepository {

    override suspend fun addFavourite(favorite: Favorite): Long {
        return favouriteDatabase.insertFavorite(favorite)
    }

    override suspend fun isFavourite(movieId: Int): Flow<Boolean> {
        return favouriteDatabase.isFavorite(movieId).flowOn(Dispatchers.IO)
    }

}