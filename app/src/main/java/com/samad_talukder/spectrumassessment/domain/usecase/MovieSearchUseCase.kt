package com.samad_talukder.spectrumassessment.domain.usecase

import com.samad_talukder.spectrumassessment.data.repository.MovieRepository


class MovieSearchUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(
        query: String,
        page: Int
    ) = movieRepository.getSearchMovieDataSource(query, page)
}