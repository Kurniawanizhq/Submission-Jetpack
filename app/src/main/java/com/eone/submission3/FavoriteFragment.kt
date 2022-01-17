package com.eone.submission3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.eone.submission3.databinding.FragmentFavoriteBinding
import com.eone.submission3.ui.adapter.FavoritePagerAdapter
import com.eone.submission3.ui.adapter.HomePagerAdapter
import com.eone.submission3.ui.home.fragment.favorite.FavoriteMovieFragment
import com.eone.submission3.ui.home.fragment.favorite.FavoriteTvShowFragment
import com.eone.submission3.ui.home.fragment.favorite.FavoriteViewModel
import com.eone.submission3.utils.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = requireNotNull(_binding)
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModelFactory = ViewModelFactory.getInstance(requireContext())

        viewModel = ViewModelProvider(this, viewModelFactory)[FavoriteViewModel::class.java]

        binding.apply {
            vpFavorite.adapter = FavoritePagerAdapter(activity as AppCompatActivity)
            TabLayoutMediator(tlFavorite, vpFavorite) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val fragmentList = listOf(FavoriteMovieFragment(),FavoriteTvShowFragment())
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tvShow)
    }
}