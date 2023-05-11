package com.samad_talukder.spectrumassessment.domain.usecase

import com.samad_talukder.spectrumassessment.data.repository.MovieRepository

class MovieByCategoryUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(
        movieCategory: String,
        page: Int
    ) = movieRepository.getMovieByCategoryRepo(movieCategory, page)
}