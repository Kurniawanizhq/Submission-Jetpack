package com.eone.submission3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eone.submission3.BuildConfig
import com.eone.submission3.R
import com.eone.submission3.databinding.ListMovieBinding
import com.eone.submission3.local.MovieEntity
import com.eone.submission3.ui.home.HomeCallback

class HomeAdapter(private val movieCallback: HomeCallback.OnItemClickedMovie) : PagingDataAdapter<MovieEntity,HomeAdapter.MovieViewHolder>(
    DIFF_CALLBACK)  {
//    private var listMovies = ArrayList<MovieEntity>()
//
//    fun setMovies(movies: List<MovieEntity>?) {
//        if (movies == null) return
//        listMovies.clear()
//        listMovies.addAll(movies)
//    }

    inner class MovieViewHolder(private val binding: ListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            binding.apply {
                tvTitleHome.text = movie.title
                itemCard.setOnClickListener {
                    movieCallback.onItemClickedMovie(movie)
                }
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + movie.posterPath)
                    .placeholder(R.drawable.picture_placeholder_small)
                    .error(BuildConfig.IMAGE_URL)
                    .into(ivItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMoviesBinding =
            ListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
//        val movie = listMovies[position]
        if (movie != null){
            holder.bind(movie)
        }
    }

//    override fun getItemCount(): Int = listMovies.size

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}