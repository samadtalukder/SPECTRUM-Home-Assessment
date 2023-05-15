package com.samad_talukder.spectrumassessment.data.api

import com.samad_talukder.spectrumassessment.domain.model.GenreMovieListResponse
import com.samad_talukder.spectrumassessment.domain.model.MovieDetailsResponse
import com.samad_talukder.spectrumassessment.domain.model.MovieResponse
import com.samad_talukder.spectrumassessment.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpectrumApi {

    @GET("movie/{category}")
    suspend fun getMovieByCategory(
        @Path("category") movieCategory: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieResponse>

    @GET("movie/{movieID}")
    suspend fun getMovieDetailsByID(
        @Path("movieID") movieID: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieDetailsResponse>

    @GET("genre/movie/list")
    suspend fun getGenreMovieList(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<GenreMovieListResponse>

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieResponse>

}