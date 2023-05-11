package com.samad_talukder.spectrumassessment.data.remote

import com.samad_talukder.spectrumassessment.domain.model.GenreMovieListResponse
import com.samad_talukder.spectrumassessment.domain.model.MovieDetailsResponse
import com.samad_talukder.spectrumassessment.domain.model.MovieResponse
import retrofit2.Response

interface MovieDataSource {
    suspend fun getMovieByCategoryDataSource(movieCategory: String, page: Int): Response<MovieResponse>
    suspend fun getMovieDetailsByIDDataSource(movieID: Int): Response<MovieDetailsResponse>
    suspend fun getGenreMovieListDataSource(): Response<GenreMovieListResponse>
    suspend fun getSearchMovieDataSource(query: String, page: Int): Response<MovieResponse>
}