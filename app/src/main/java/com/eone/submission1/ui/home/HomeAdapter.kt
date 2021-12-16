package com.eone.submission1.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eone.submission1.databinding.ListMovieBinding
import com.eone.submission1.model.DataEntity

class HomeAdapter(private val homeCallback: HomeCallback) :
    RecyclerView.Adapter<HomeAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<DataEntity>()

    fun setMovies(movies: List<DataEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    inner class MovieViewHolder(private val binding: ListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: DataEntity) {
            binding.apply {
                tvTitleHome.text = movie.title
                tvGenreHome.text = movie.genre
                Glide.with(itemView.context)
                    .load(movie.poster)
                    .into(ivItem)
                itemCard.setOnClickListener {
                    homeCallback.onItemClicked(movie)
                }
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