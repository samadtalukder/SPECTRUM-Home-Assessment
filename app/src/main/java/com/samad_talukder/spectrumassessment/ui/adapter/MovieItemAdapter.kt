package com.samad_talukder.spectrumassessment.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.samad_talukder.spectrumassessment.databinding.ItemMovieBinding
import com.samad_talukder.spectrumassessment.domain.model.Movie
import com.samad_talukder.spectrumassessment.utils.convertDate
import com.samad_talukder.spectrumassessment.utils.getGenreText
import com.samad_talukder.spectrumassessment.utils.loadImage


class MovieItemAdapter(private val context: Context) :
    RecyclerView.Adapter<MovieItemAdapter.MovieItemViewHolder>() {
    var onClick: ((Int) -> Unit)? = null

    private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var jobList: List<Movie>
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
        fun bind(movie: Movie) {
            itemBinding.apply {
                tvTitle.text = movie.title
                tvReleaseDate.text = convertDate(movie.release_date)
                tvVoteCount.text = "${movie.vote_count} Votes"
                tvRating.text = movie.vote_average.toString()

                for (i in movie.genre_ids) {
                    tvGenre.text = getGenreText(i)

                }

                if (!movie.poster_path.isNullOrEmpty()) {
                    loadImage(context, movie.poster_path, ivPoster)
                }

                cvPoster.setOnClickListener {
                    onClick?.invoke(
                        movie.id
                    )
                }
            }

        }
    }
}