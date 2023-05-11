package com.samad_talukder.spectrumassessment.domain.usecase

import com.samad_talukder.spectrumassessment.data.repository.MovieRepository


class MovieDetailsByIDUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(movieID: Int) = movieRepository.getMovieDetailsByIDRepo(movieID)
}