package com.eone.submission3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eone.submission3.BuildConfig
import com.eone.submission3.data.response.ItemListResponse
import com.eone.submission3.R
import com.eone.submission3.databinding.ListMovieBinding
import com.eone.submission3.local.MovieEntity
import com.eone.submission3.ui.home.HomeCallback

class HomeAdapter(private val homeCallback: HomeCallback) :
   PagedListAdapter<MovieEntity,HomeAdapter.MovieViewHolder>(DIFF_CALLBACK) {
//    private var listMovies = ArrayList<ItemListResponse>()

//    fun setMovies(movies: List<ItemListResponse>?) {
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
                    homeCallback.onItemClickedMovie(movie)
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
        if (movie != null){
            holder.bind(movie)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}