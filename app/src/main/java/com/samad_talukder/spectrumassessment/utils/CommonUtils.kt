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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.Objects.isNull

@RequiresApi(Build.VERSION_CODES.O)
fun convertDate(date: String): String {
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
