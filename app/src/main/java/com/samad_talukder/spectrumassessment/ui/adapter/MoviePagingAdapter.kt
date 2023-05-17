package com.samad_talukder.spectrumassessment.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.samad_talukder.spectrumassessment.databinding.ItemMovieBinding
import com.samad_talukder.spectrumassessment.domain.model.Movie
import com.samad_talukder.spectrumassessment.utils.convertDate
import com.samad_talukder.spectrumassessment.utils.getGenreText
import com.samad_talukder.spectrumassessment.utils.loadImage

class MoviePagingAdapter(private val context: Context) :
    PagingDataAdapter<Movie, MoviePagingAdapter.MovieViewHolder>(MovieDiffCallback()) {
    var onClick: ((Int) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }

    inner class MovieViewHolder(private val itemBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie) {
            itemBinding.apply {
                tvTitle.text = movie.title
                tvReleaseDate.text = convertDate(movie.release_date)
                tvVoteCount.text = "${movie.vote_count} Votes"
                tvRating.text = movie.vote_average.toString()

                for (i in movie.genre_ids) {
                    tvGenre.text = getGenreText(i)

                }

                context?.let { loadImage(it, movie.poster_path, ivPoster) }

                cvPoster.setOnClickListener {
                    onClick?.invoke(
                        movie.id
                    )
                }
            }

        }
    }

}


class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}
