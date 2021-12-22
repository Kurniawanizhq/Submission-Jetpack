package com.eone.submission1.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.eone.submission1.ItemDetailResponse
import com.eone.submission1.ItemListResponse
import com.eone.submission1.ViewModelFactory
import com.eone.submission1.ui.home.HomeCallback
import com.eone.submission1.databinding.FragmentTvShowBinding
import com.eone.submission1.model.DataEntity
import com.eone.submission1.ui.detail.DetailActivity
import com.eone.submission1.ui.home.HomeAdapter
import com.eone.submission1.ui.home.HomeViewModel

class TvShowFragment : Fragment(), HomeCallback {
    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showProgressBar(true)
            val viewModelFactory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(
                this,
                viewModelFactory
            )[HomeViewModel::class.java]
            viewModel.getTvShow()?.observe(viewLifecycleOwner, Observer {
                showProgressBar(false)
                setLayout(it)
            })
        }
    }

    private fun setLayout(data: List<ItemListResponse>) {
        binding.rvTvShow.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = HomeAdapter(this@TvShowFragment)
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
                .putExtra(DetailActivity.EXTRA_TYPE,"TV_SHOW")
        )
    }

    private fun showProgressBar(state: Boolean) {
        binding.rlTvShow.isVisible = state
        binding.rvTvShow.isInvisible = state
    }
}