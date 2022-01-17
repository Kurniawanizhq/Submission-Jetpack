package com.eone.submission3.ui.home.fragment.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.eone.submission3.databinding.FragmentFavoriteTvShowBinding
import com.eone.submission3.local.TvShowEntity
import com.eone.submission3.ui.adapter.HomeAdapter
import com.eone.submission3.ui.adapter.TvShowAdapter
import com.eone.submission3.ui.detail.DetailActivity
import com.eone.submission3.ui.home.HomeCallback
import com.eone.submission3.utils.ViewModelFactory
import kotlinx.coroutines.launch

class FavoriteTvShowFragment : Fragment(),HomeCallback.OnItemClickedTvshow {

    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = requireNotNull(_binding)
    private lateinit var viewModel : FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModelFactory = ViewModelFactory.getInstance(requireContext())

        viewModel = ViewModelProvider(this,viewModelFactory)[FavoriteViewModel::class.java]

        getFavorite()

    }

    private fun getFavorite() {
        binding.apply {
            rvFavoriteTvshow.layoutManager = GridLayoutManager(context, 2)
            rvFavoriteTvshow.adapter = TvShowAdapter(this@FavoriteTvShowFragment)
            viewModel.getFavoriteTvshow().observe(viewLifecycleOwner) {
                println("Favorite TVshow : $it")
                if (it != null) {
                    rvFavoriteTvshow.adapter.let { adapter ->
                        if (adapter is TvShowAdapter) {
                            rvFavoriteTvshow.visibility = View.VISIBLE
                            viewModel.viewModelScope.launch {
                                adapter.submitData(it)
                            }
                        }
                    }
                } else {
                    rvFavoriteTvshow.visibility = View.GONE
                }
            }
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