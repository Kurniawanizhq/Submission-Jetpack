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
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.eone.submission3.databinding.FragmentMovieBinding
import com.eone.submission3.local.MovieEntity
import com.eone.submission3.ui.adapter.MovieAdapter
import com.eone.submission3.ui.detail.DetailActivity
import com.eone.submission3.ui.home.HomeCallback
import com.eone.submission3.ui.home.HomeViewModel
import com.eone.submission3.utils.SortUtils
import com.eone.submission3.utils.ViewModelFactory
import com.eone.submission3.vo.Status
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.launch


class MovieFragment : Fragment(), HomeCallback.OnItemClickedMovie {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = requireNotNull(_binding)
    private lateinit var viewModel: HomeViewModel

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
            viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

            setList(SortUtils.POPULARITY)

            binding.apply {
                fabPopularity.setOnClickListener { setList(SortUtils.POPULARITY) }
                fabNewest.setOnClickListener { setList(SortUtils.NEWEST) }
                fabOldest.setOnClickListener { setList(SortUtils.OLDEST) }
            }


        }
    }

    private fun setList(sort: String) {
        viewModel.getMovies(sort).observe(viewLifecycleOwner, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        showProgressBar(true)
                    }
                    Status.SUCCES -> {
                        if (it.data != null) {
                            showProgressBar(false)
                            viewLifecycleOwner.lifecycleScope.launch {
                                setRecyclerView(it.data)
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

    private suspend fun setRecyclerView(data: PagingData<MovieEntity>) {
        binding.rvMovie.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = MovieAdapter(this@MovieFragment)
        }.also {
            it.adapter.let { adapter ->
                when (adapter) {
                    is MovieAdapter -> {
                        adapter.submitData(data)
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
}