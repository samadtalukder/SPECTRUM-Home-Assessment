package com.samad_talukder.spectrumassessment.data.repository

import com.samad_talukder.spectrumassessment.data.api.ApiResult
import com.samad_talukder.spectrumassessment.domain.model.GenreMovieListResponse
import com.samad_talukder.spectrumassessment.domain.model.MovieDetailsResponse
import com.samad_talukder.spectrumassessment.domain.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieByCategoryRepo(
        movieCategory: String,
        page: Int
    ): Flow<ApiResult<MovieResponse>>

    suspend fun getMovieDetailsByIDRepo(
        movieID: Int
    ): Flow<ApiResult<MovieDetailsResponse>>

    suspend fun getGenreMovieListRepo(): Flow<ApiResult<GenreMovieListResponse>>

    suspend fun getSearchMovieDataSource(
        query: String,
        page: Int
    ): Flow<ApiResult<MovieResponse>>


}