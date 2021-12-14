package com.eone.submission1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eone.submission1.data.Callback
import com.eone.submission1.databinding.ListMovieBinding
import com.eone.submission1.model.DataEntity

class MovieAdapter(private val callback : Callback) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<DataEntity>()

    fun setMovies(movies : List<DataEntity>?){
        if (movies==null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    inner class MovieViewHolder (private val binding : ListMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie : DataEntity){
            binding.apply {
                tvTitleHome.text = movie.title
                tvGenreHome.text = movie.genre
                itemView.setOnClickListener {

                }
                Glide.with(itemView.context)
                    .load(movie.poster)
//                    .apply(
//                        RequestOptions.placeholderOf(R.drawable.ic_loading)
//                        .error(R.drawable.ic_error))
                    .into(ivItem)

                itemCard.setOnClickListener {
                    callback.onItemClicked(movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
val itemsMoviesBinding = ListMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return MovieViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size
}