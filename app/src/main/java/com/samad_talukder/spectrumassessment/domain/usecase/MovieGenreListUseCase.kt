package com.samad_talukder.spectrumassessment.domain.usecase

import com.samad_talukder.spectrumassessment.data.repository.MovieRepository


class MovieGenreListUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute() = movieRepository.getGenreMovieListRepo()
}