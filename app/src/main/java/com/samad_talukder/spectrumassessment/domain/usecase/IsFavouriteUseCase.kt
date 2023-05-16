package com.samad_talukder.spectrumassessment.domain.usecase

import com.samad_talukder.spectrumassessment.data.repository.FavouriteRepository

class IsFavouriteUseCase(private val favoriteRepository: FavouriteRepository) {

    suspend fun execute(
        movieId: Int
    ) = favoriteRepository.isFavourite(movieId)
}

