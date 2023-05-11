package com.samad_talukder.spectrumassessment.di


import com.samad_talukder.spectrumassessment.data.api.SpectrumApi
import com.samad_talukder.spectrumassessment.data.remote.MovieDataSource
import com.samad_talukder.spectrumassessment.data.remote.MovieDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideMovieDataSource(spectrumApi: SpectrumApi): MovieDataSource {
        return MovieDataSourceImpl(spectrumApi)
    }
}