package com.eone.submission3

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eone.submission3.data.response.ItemListResponse
import com.eone.submission3.databinding.ListMovieBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewModel>() {
    private var listFavorite = ArrayList<ItemListResponse>()
   inner class FavoriteViewModel (binding : ListMovieBinding):RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewModel {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FavoriteViewModel, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}