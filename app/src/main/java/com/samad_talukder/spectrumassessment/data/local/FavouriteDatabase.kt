package com.samad_talukder.spectrumassessment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samad_talukder.spectrumassessment.data.local.dao.FavouritesDao
import com.samad_talukder.spectrumassessment.data.local.entity.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class FavouriteDatabase : RoomDatabase() {
    abstract fun favouriteDao(): FavouritesDao

}