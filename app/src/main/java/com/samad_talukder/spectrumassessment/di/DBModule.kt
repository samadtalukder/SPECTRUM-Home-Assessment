package com.samad_talukder.spectrumassessment.di

import android.app.Application
import androidx.room.Room
import com.samad_talukder.spectrumassessment.data.local.FavouriteDatabase
import com.samad_talukder.spectrumassessment.data.local.dao.FavouritesDao
import com.samad_talukder.spectrumassessment.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideFavoritesDatabase(application: Application): FavouriteDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            FavouriteDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideFavoriteDao(database: FavouriteDatabase): FavouritesDao {
        return database.favouriteDao()
    }
}