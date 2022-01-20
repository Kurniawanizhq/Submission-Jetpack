package com.eone.submission3.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.eone.submission3.databinding.FragmentFavoriteTvShowBinding
import com.eone.submission3.data.local.entity.TvShowEntity
import com.eone.submission3.ui.adapter.TvShowAdapter
import com.eone.submission3.ui.detail.DetailActivity
import com.eone.submission3.ui.home.HomeCallback
import com.eone.submission3.utils.ViewModelFactory

class FavoriteTvShowFragment : Fragment(), HomeCallback.OnItemClickedTvshow {

    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = requireNotNull(_binding)
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModelFactory = ViewModelFactory.getInstance(requireContext())

        viewModel = ViewModelProvider(this, viewModelFactory)[FavoriteViewModel::class.java]

        getFavorite()

    }

    private fun getFavorite() {
        binding.apply {
            rvFavoriteTvshow.layoutManager = GridLayoutManager(context, 2)
            rvFavoriteTvshow.adapter = TvShowAdapter(this@FavoriteTvShowFragment)
            viewModel.getFavoriteTvshow().observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()) {
                    showEmptyFavorite(false)
                    rvFavoriteTvshow.adapter.let { adapter ->
                        if (adapter is TvShowAdapter) {
                            adapter.submitList(it)
                        }
                    }
                } else {
                    showEmptyFavorite(true)
                }
            }
        }
    }

    private fun showEmptyFavorite(state : Boolean){
        binding.apply {
            rvFavoriteTvshow.isInvisible = state
            imgEmpty.isInvisible = !state
            titleEmptyState.isInvisible = !state
            descEmpty.isInvisible = !state
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClickedTvshow(data: TvShowEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_ID, data.tvshowId)
                .putExtra(DetailActivity.EXTRA_TYPE, "TV_SHOW")
        )
    }

}