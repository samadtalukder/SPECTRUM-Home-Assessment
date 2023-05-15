package com.samad_talukder.spectrumassessment.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.samad_talukder.spectrumassessment.R
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.Objects.isNull

@RequiresApi(Build.VERSION_CODES.O)
fun convertDate(date: String): String {
    if (date.isBlank()) {
        return ""
    }
    val mDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    return mDate.format(formatter)

}

fun loadImage(context: Context, imageUrl: String, imageView: ImageView) {
    Glide.with(context)
        .load(Constants.BASE_IMAGE_URL + imageUrl)
        .placeholder(R.drawable.ic_logo)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
        .into(imageView)
}

fun ratingFormat(rating: Double): String {
    val df: DecimalFormat = getDecimalFormat("#.#")
    return df.format(rating)
}

fun getDecimalFormat(pattern: String): DecimalFormat {
    return DecimalFormat(pattern, DecimalFormatSymbols(Locale.US))
}

fun getGenreText(genreIds: Int): String {
    val genreMap = mapOf(
        28 to "Action",
        12 to "Adventure",
        16 to "Animation",
        35 to "Comedy",
        80 to "Crime",
        99 to "Documentary",
        18 to "Drama",
        10751 to "Family",
        14 to "Fantasy",
        36 to "History",
        27 to "Horror",
        10402 to "Music",
        9648 to "Mystery",
        10749 to "Romance",
        878 to "Science Fiction",
        10770 to "TV Movie",
        53 to "Thriller",
        10752 to "War",
        37 to "Western"
    )

    return genreMap[genreIds] ?: "Unknown Genre"
}