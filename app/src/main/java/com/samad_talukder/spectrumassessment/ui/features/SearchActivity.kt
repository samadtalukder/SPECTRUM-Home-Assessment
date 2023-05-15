package com.samad_talukder.spectrumassessment.ui.features

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.samad_talukder.spectrumassessment.R
import com.samad_talukder.spectrumassessment.data.api.ApiResult
import com.samad_talukder.spectrumassessment.databinding.ActivityMainBinding
import com.samad_talukder.spectrumassessment.databinding.ActivitySearchBinding
import com.samad_talukder.spectrumassessment.domain.model.Movie
import com.samad_talukder.spectrumassessment.ui.adapter.MovieItemAdapter
import com.samad_talukder.spectrumassessment.ui.viewmodel.MovieByCategoryViewModel
import com.samad_talukder.spectrumassessment.ui.viewmodel.MovieSearchViewModel
import com.samad_talukder.spectrumassessment.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val movieSearchViewModel: MovieSearchViewModel by viewModels()
    private lateinit var movieItemAdapter: MovieItemAdapter
    private var searchStr: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchStr = intent.getStringExtra(Constants.INTENT_KEY_SEARCH_DATA)

        if (searchStr != null) {
            binding.ivBack.setOnClickListener { onBackPressed() }

            binding.apply {
                rvSearch.visibility = View.VISIBLE

                val layoutManager = GridLayoutManager(this@SearchActivity, 2)
                rvSearch.layoutManager = layoutManager
                rvSearch.setHasFixedSize(true)

                movieItemAdapter = MovieItemAdapter(this@SearchActivity)
                rvSearch.adapter = movieItemAdapter

            }

            observeMovieSearch()
            callSearchAPI(searchStr)
        }

    }

    private fun callSearchAPI(query: String?) {
        if (query != null) {
            movieSearchViewModel.getSearchMovie(query, 1)
        }
    }

    private fun observeMovieSearch() {
        movieSearchViewModel.movieSearchResponse.observe(this) { response ->

            when (response) {
                is ApiResult.Loading -> {}

                is ApiResult.Success -> {

                    if (!Objects.isNull(response.data)) {

                        response.data.let {
                            if (it.results.isNotEmpty()) {
                                showList(it.results)
                            }
                        }
                    }

                }
                is ApiResult.Error -> {
                    Toast.makeText(this, response.errorMessage,Toast.LENGTH_SHORT).show()
                }

            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showList(results: List<Movie>) {
        binding.apply {
            rvSearch.visibility = View.VISIBLE
            movieItemAdapter.jobList += results
            movieItemAdapter.notifyDataSetChanged()

            movieItemAdapter.onClick = { movieID ->
                val intent = Intent(this@SearchActivity, MovieDetailsActivity::class.java)
                intent.putExtra(Constants.MOVIE_ID, movieID)
                startActivity(intent)
            }
        }
    }
}