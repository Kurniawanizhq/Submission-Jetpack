package com.eone.submission3.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eone.submission3.BuildConfig
import com.eone.submission3.data.response.ItemListResponse
import com.eone.submission3.R
import com.eone.submission3.databinding.ListMovieBinding

class HomeAdapter(private val homeCallback: HomeCallback) :
    RecyclerView.Adapter<HomeAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<ItemListResponse>()

    fun setMovies(movies: List<ItemListResponse>?) {
        if (movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
    }

    inner class MovieViewHolder(private val binding: ListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ItemListResponse) {
            binding.apply {
                tvTitleHome.text = movie.title ?: movie.name
                itemCard.setOnClickListener {
                    homeCallback.onItemClicked(movie)
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
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size
}