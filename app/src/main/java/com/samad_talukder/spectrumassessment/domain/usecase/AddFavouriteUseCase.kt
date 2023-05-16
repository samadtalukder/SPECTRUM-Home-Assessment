package com.samad_talukder.spectrumassessment.domain.usecase

import com.samad_talukder.spectrumassessment.data.api.ApiResult
import com.samad_talukder.spectrumassessment.data.local.entity.Favorite
import com.samad_talukder.spectrumassessment.data.repository.FavouriteRepository

class AddFavouriteUseCase(private val favoriteRepository: FavouriteRepository) {
    suspend fun execute(
        favorite: Favorite
    ): ApiResult<Long> {
        return try {
            val insertedItemId = favoriteRepository.addFavourite(favorite)
            ApiResult.Success(insertedItemId)
        } catch (e: Exception) {
            ApiResult.Error("An error occurred", 500)
        }
    }
}
