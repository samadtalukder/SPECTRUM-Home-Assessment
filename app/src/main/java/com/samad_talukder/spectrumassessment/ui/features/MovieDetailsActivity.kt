package com.samad_talukder.spectrumassessment.ui.features

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.samad_talukder.spectrumassessment.data.api.ApiResult
import com.samad_talukder.spectrumassessment.databinding.ActivityMovieDetailsBinding
import com.samad_talukder.spectrumassessment.ui.viewmodel.MovieDetailsByIDViewModel
import com.samad_talukder.spectrumassessment.utils.Constants.MOVIE_ID
import com.samad_talukder.spectrumassessment.utils.convertDate
import com.samad_talukder.spectrumassessment.utils.loadImage
import com.samad_talukder.spectrumassessment.utils.ratingFormat
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private var movieId: Int = 0
    private val movieDetailsByViewModel: MovieDetailsByIDViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieId = intent.getIntExtra(MOVIE_ID, 0)

        binding.ivBack.setOnClickListener { onBackPressed() }

        observeMovieDetails()
        callMovieDetailsAPI(movieId)

    }

    private fun callMovieDetailsAPI(movieId: Int) {
        movieDetailsByViewModel.getMovieDetailsByID(movieId)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun observeMovieDetails() {
        movieDetailsByViewModel.movieDetailsResponse.observe(this) { response ->
            when (response) {
                is ApiResult.Loading -> {}

                is ApiResult.Success -> {

                    if (!Objects.isNull(response.data)) {

                        response.data.let {
                            binding.apply {
                                tvTitle.text = it.title
                                tvStoryLine.text = it.overview
                                tvReleaseDate.text = convertDate(it.release_date) + ", "
                                tvVoteCount.text = it.vote_count.toString() + " Votes"
                                tvRating.text = ratingFormat(it.vote_average)
                                tvRelease.text = it.status

                                val language = it.spoken_languages.mapIndexed { _, spokenLanguage ->
                                    spokenLanguage.english_name
                                }
                                val genre = it.genres.mapIndexed { _, genre ->
                                    genre.name
                                }

                                tvLanguage.text = language.joinToString(", ")
                                tvGenre.text = genre.joinToString(", ")

                                if (!it.backdrop_path.isNullOrEmpty()) {
                                    loadImage(
                                        this@MovieDetailsActivity,
                                        it.backdrop_path,
                                        ivBackDrop
                                    )
                                }

                                if (!it.poster_path.isNullOrEmpty()) {
                                    loadImage(this@MovieDetailsActivity, it.poster_path, ivPoster)
                                }


                            }
                        }
                    }

                }
                is ApiResult.Error -> {
                    Log.e("SPECTRUM", "errorMessage: ${response.errorMessage} - ${response.code}")
                    Toast.makeText(this, response.errorMessage, Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}