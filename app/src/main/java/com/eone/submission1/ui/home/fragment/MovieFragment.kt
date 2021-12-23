package com.eone.submission1.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.eone.submission1.ItemListResponse
import com.eone.submission1.ViewModelFactory
import com.eone.submission1.databinding.FragmentMovieBinding
import com.eone.submission1.ui.detail.DetailActivity
import com.eone.submission1.ui.home.HomeAdapter
import com.eone.submission1.ui.home.HomeCallback
import com.eone.submission1.ui.home.HomeViewModel


class MovieFragment : Fragment(), HomeCallback {

    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showProgressBar(true)
            val viewModelFactory = ViewModelFactory.getInstance()

            val viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

            viewModel.getMovies()?.observe(viewLifecycleOwner,{
                showProgressBar(false)
                println("list Movies : $it")
                setLayout(it)
            })
        }
    }

    private fun setLayout(data: List<ItemListResponse>) {
        binding.rvMovie.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = HomeAdapter(this@MovieFragment)
        }.also {
            it.adapter.let { adapter ->
                when (adapter) {
                    is HomeAdapter -> {
                        adapter.setMovies(data)
                    }
                }

            }
        }
    }

    override fun onItemClicked(data: ItemListResponse) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_ID,data.id)
                .putExtra(DetailActivity.EXTRA_TYPE,"MOVIE")
        )
    }

    private fun showProgressBar(state: Boolean) {
        binding.rvMovie.isInvisible = state
        if (state){
            binding.rlMovie.start()
        }else{
            binding.rlMovie.stop()
        }
    }
}