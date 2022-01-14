package com.eone.submission3.ui.home.fragment.content

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.eone.submission3.databinding.FragmentMovieBinding
import com.eone.submission3.local.MovieEntity
import com.eone.submission3.local.TvShowEntity
import com.eone.submission3.ui.adapter.HomeAdapter
import com.eone.submission3.ui.detail.DetailActivity
import com.eone.submission3.ui.home.HomeCallback
import com.eone.submission3.ui.home.HomeViewModel
import com.eone.submission3.utils.ViewModelFactory
import com.eone.submission3.vo.Resource
import com.eone.submission3.vo.Status
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.launch


class MovieFragment : Fragment(), HomeCallback {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModelFactory = ViewModelFactory.getInstance(requireContext())

            val viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

            viewModel.getMovies().observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> {
                            showProgressBar(true)
                        }
                        Status.SUCCES -> {
                            if (it.data != null) {
                                showProgressBar(false)
                                viewLifecycleOwner.lifecycleScope.launch {
                                    setLayout(it)
                                }
                            }
                        }
                        Status.ERROR -> {
                            showProgressBar(false)
                            FancyToast.makeText(
                                context,
                                "Error memuat data",
                                Toast.LENGTH_SHORT,
                                FancyToast.ERROR,
                                false
                            ).show()
                        }
                    }

                }
            })
        }
    }

    private suspend fun setLayout(data: Resource<PagingData<MovieEntity>>) {
        binding.rvMovie.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = HomeAdapter(this@MovieFragment)
        }.also {
            it.adapter.let { adapter ->
                when (adapter) {
                    is HomeAdapter -> {
                        data.data?.let { it1 -> adapter.submitData(it1) }
                    }
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showProgressBar(state: Boolean) {
        binding.rvMovie.isInvisible = state
        if (state) {
            binding.rlMovie.start()
        } else {
            binding.rlMovie.stop()
        }
    }

    override fun onItemClickedMovie(data: MovieEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_ID, data.movieId)
                .putExtra(DetailActivity.EXTRA_TYPE, "MOVIE")
        )
    }

    override fun onItemClickedTvshow(data: TvShowEntity) {
        TODO("Not yet implemented")
    }
}