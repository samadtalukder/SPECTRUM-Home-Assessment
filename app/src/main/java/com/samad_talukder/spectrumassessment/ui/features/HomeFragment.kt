package com.samad_talukder.spectrumassessment.ui.features

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.samad_talukder.spectrumassessment.data.api.ApiResult
import com.samad_talukder.spectrumassessment.databinding.FragmentHomeBinding
import com.samad_talukder.spectrumassessment.domain.model.MovieItem
import com.samad_talukder.spectrumassessment.ui.adapter.MovieItemAdapter
import com.samad_talukder.spectrumassessment.ui.viewmodel.MovieByCategoryViewModel
import com.samad_talukder.spectrumassessment.utils.Constants.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val movieByCategoryViewModel: MovieByCategoryViewModel by viewModels()
    private lateinit var movieItemAdapter: MovieItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeMovieByCategory()

        binding.apply {
            tvError.visibility = View.GONE
            rvJobList.visibility = View.VISIBLE

            val layoutManager = GridLayoutManager(requireActivity(), 2)
            rvJobList.layoutManager = layoutManager
            rvJobList.setHasFixedSize(true)

            movieItemAdapter = MovieItemAdapter(requireActivity())
            rvJobList.adapter = movieItemAdapter

        }

        val category = "now_playing"
        val page = 1
        callMovieByCategoryAPI(category, page)
    }

    private fun callMovieByCategoryAPI(category: String, page: Int) {
        movieByCategoryViewModel.getMovieByCategory(category, page)
    }

    private fun observeMovieByCategory() {
        movieByCategoryViewModel.movieCategoryResponse.observe(this) { response ->

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
                is ApiResult.Error -> {}

            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showList(results: List<MovieItem>) {

        binding.apply {
            rvJobList.visibility = View.VISIBLE
            movieItemAdapter.jobList += results
            movieItemAdapter.notifyDataSetChanged()

            movieItemAdapter.onClick = { movieID ->
                val intent = Intent(context, MovieDetailsActivity::class.java)
                intent.putExtra(MOVIE_ID, movieID)
                startActivity(intent)
            }
        }
    }
}