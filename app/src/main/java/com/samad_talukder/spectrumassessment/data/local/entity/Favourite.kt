package com.samad_talukder.spectrumassessment.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.samad_talukder.spectrumassessment.utils.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Favorite(
    val favorite: Boolean,
    @PrimaryKey val movieId: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
)