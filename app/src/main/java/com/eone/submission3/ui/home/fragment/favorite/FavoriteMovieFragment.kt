package com.eone.submission3.ui.home.fragment.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.eone.submission3.databinding.FragmentFavoriteMovieBinding
import com.eone.submission3.local.MovieEntity
import com.eone.submission3.ui.adapter.MovieAdapter
import com.eone.submission3.ui.detail.DetailActivity
import com.eone.submission3.ui.home.HomeCallback
import com.eone.submission3.utils.ViewModelFactory

class FavoriteMovieFragment : Fragment(), HomeCallback.OnItemClickedMovie {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = requireNotNull(_binding)
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[FavoriteViewModel::class.java]

        getFavorite()
    }


    private fun getFavorite() {
        binding.apply {
            rvFavoriteMovie.layoutManager = GridLayoutManager(context, 2)
            rvFavoriteMovie.adapter = MovieAdapter(this@FavoriteMovieFragment)
            viewModel.getFavoriteMovies().observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()) {
                    showEmptyFavorite(false)
                    rvFavoriteMovie.adapter.let { adapter ->
                        if (adapter is MovieAdapter) {
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
            rvFavoriteMovie.isInvisible = state
            imgEmpty.isInvisible = !state
            titleEmptyState.isInvisible = !state
            descEmpty.isInvisible = !state
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClickedMovie(data: MovieEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_ID, data.movieId)
                .putExtra(DetailActivity.EXTRA_TYPE, "MOVIE")
        )
    }

}