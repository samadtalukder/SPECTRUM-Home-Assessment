package com.samad_talukder.spectrumassessment.di


import com.samad_talukder.spectrumassessment.data.remote.MovieDataSource
import com.samad_talukder.spectrumassessment.data.repository.MovieRepository
import com.samad_talukder.spectrumassessment.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesMovieRepository(movieDataSource: MovieDataSource): MovieRepository {
        return MovieRepositoryImpl(movieDataSource)
    }
}