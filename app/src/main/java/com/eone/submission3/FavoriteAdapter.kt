package com.eone.submission3

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eone.submission3.data.response.ItemResponse
import com.eone.submission3.databinding.ListMovieBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    class FavoriteViewHolder (binding : ListMovieBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(item : ItemResponse){

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}