package com.samad_talukder.spectrumassessment.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samad_talukder.spectrumassessment.R
import com.samad_talukder.spectrumassessment.databinding.ItemMovieBinding
import com.samad_talukder.spectrumassessment.domain.model.MovieItem
import com.samad_talukder.spectrumassessment.utils.Constants
import com.samad_talukder.spectrumassessment.utils.convertDate
import com.samad_talukder.spectrumassessment.utils.loadImage


class MovieItemAdapter(private val context: Context) :
    RecyclerView.Adapter<MovieItemAdapter.MovieItemViewHolder>() {
    var onClick: ((Int) -> Unit)? = null

    private val diffCallback = object : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem) = oldItem == newItem
        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem) = oldItem == newItem
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var jobList: List<MovieItem>
        get() = differ.currentList
        set(value) = differ.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val itemBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {

        holder.bind(jobList[position])
    }

    inner class MovieItemViewHolder(private val itemBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SetTextI18n")
        fun bind(movieItem: MovieItem) {
            itemBinding.apply {
                tvTitle.text = movieItem.title
                tvReleaseDate.text = convertDate(movieItem.release_date)
                tvVoteCount.text = "${movieItem.vote_count} Votes"
                tvRating.text = movieItem.vote_average.toString()

                for (i in movieItem.genre_ids) {
                    tvGenre.text = getGenreText(i)

                }

                loadImage(context, movieItem.poster_path, ivPoster)

                cvPoster.setOnClickListener {
                    onClick?.invoke(
                        movieItem.id
                    )
                }
            }

        }

        private fun getGenreText(genreIds: Int): String {
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

    }
}