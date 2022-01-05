package com.eone.submission3.ui.home.fragment.content

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.eone.submission3.databinding.FragmentTvShowBinding
import com.eone.submission3.local.MovieEntity
import com.eone.submission3.local.TvShowEntity
import com.eone.submission3.ui.adapter.TvShowAdapter
import com.eone.submission3.ui.detail.DetailActivity
import com.eone.submission3.ui.home.HomeCallback
import com.eone.submission3.ui.home.HomeViewModel
import com.eone.submission3.utils.ViewModelFactory
import com.eone.submission3.vo.Resource

class TvShowFragment : Fragment(), HomeCallback {
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showProgressBar(true)
            val viewModelFactory = ViewModelFactory.getInstance(context as Context)
            val viewModel = ViewModelProvider(
                this,
                viewModelFactory
            )[HomeViewModel::class.java]
            viewModel.getTvShow().observe(viewLifecycleOwner,{
                showProgressBar(false)
                setLayout(it)
            })
        }
    }

    private fun setLayout(data: Resource<PagedList<TvShowEntity>>) {
        binding.rvTvShow.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = TvShowAdapter(this@TvShowFragment)
        }.also {
            it.adapter.let { adapter ->
                when (adapter) {
                    is TvShowAdapter -> {
                        adapter.submitList(data.data)
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
        binding.rvTvShow.isInvisible = state
        if (state){
            binding.rlTvShow.start()
        }else{
            binding.rlTvShow.stop()
        }
    }

    override fun onItemClickedMovie(data: MovieEntity) {
        TODO("Not yet implemented")
    }

    override fun onItemClickedTvshow(data: TvShowEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_ID,data.tvshowId)
                .putExtra(DetailActivity.EXTRA_TYPE,"TV_SHOW")
        )
    }
}