package com.samad_talukder.spectrumassessment.di

import com.samad_talukder.spectrumassessment.data.repository.FavouriteRepository
import com.samad_talukder.spectrumassessment.data.repository.MovieRepository
import com.samad_talukder.spectrumassessment.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideMovieByCategoryUseCase(movieRepository: MovieRepository): MovieByCategoryUseCase {
        return MovieByCategoryUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideMovieDetailsByIDUseCase(movieRepository: MovieRepository): MovieDetailsByIDUseCase {
        return MovieDetailsByIDUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideMovieGenreListUseCase(movieRepository: MovieRepository): MovieGenreListUseCase {
        return MovieGenreListUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideMovieSearchUseCase(movieRepository: MovieRepository): MovieSearchUseCase {
        return MovieSearchUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideAddFavouriteUseCase(movieRepository: FavouriteRepository): AddFavouriteUseCase {
        return AddFavouriteUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideIsFavouriteUseCase(movieRepository: FavouriteRepository): IsFavouriteUseCase {
        return IsFavouriteUseCase(movieRepository)
    }
}