package com.samad_talukder.spectrumassessment.data.repository

import com.samad_talukder.spectrumassessment.data.api.ApiResult
import com.samad_talukder.spectrumassessment.data.api.BaseApiResponse
import com.samad_talukder.spectrumassessment.data.remote.MovieDataSource
import com.samad_talukder.spectrumassessment.domain.model.GenreMovieListResponse
import com.samad_talukder.spectrumassessment.domain.model.MovieDetailsResponse
import com.samad_talukder.spectrumassessment.domain.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieDataSource: MovieDataSource) :
    BaseApiResponse(), MovieRepository {

    override suspend fun getMovieByCategoryRepo(
        movieCategory: String,
        page: Int
    ): Flow<ApiResult<MovieResponse>> {
        return flow {
            emit(safeApiCall { movieDataSource.getMovieByCategoryDataSource(movieCategory, page) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMovieDetailsByIDRepo(movieID: Int): Flow<ApiResult<MovieDetailsResponse>> {
        return flow {
            emit(
                safeApiCall { movieDataSource.getMovieDetailsByIDDataSource(movieID) }
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getGenreMovieListRepo(): Flow<ApiResult<GenreMovieListResponse>> {
        return flow {
            emit(
                safeApiCall { movieDataSource.getGenreMovieListDataSource() }
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getSearchMovieDataSource(
        query: String,
        page: Int
    ): Flow<ApiResult<MovieResponse>> {
        return flow {
            emit(
                safeApiCall { movieDataSource.getSearchMovieDataSource(query, page) }
            )
        }.flowOn(Dispatchers.IO)
    }
}