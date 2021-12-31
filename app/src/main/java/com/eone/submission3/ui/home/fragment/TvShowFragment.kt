package com.eone.submission3.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.eone.submission3.databinding.FragmentTvShowBinding
import com.eone.submission3.ui.detail.DetailActivity
import com.eone.submission3.ui.home.HomeAdapter
import com.eone.submission3.ui.home.HomeCallback
import com.eone.submission3.ui.home.HomeViewModel
import com.eone.submission3.utils.ViewModelFactory

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
            val viewModelFactory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(
                this,
                viewModelFactory
            )[HomeViewModel::class.java]
            viewModel.getTvShow()?.observe(viewLifecycleOwner,{
                showProgressBar(false)
                setLayout(it)
            })
        }
    }

    private fun setLayout(data: List<com.eone.submission3.data.response.ItemListResponse>) {
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

    override fun onItemClicked(data: com.eone.submission3.data.response.ItemListResponse) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_ID,data.id)
                .putExtra(DetailActivity.EXTRA_TYPE,"TV_SHOW")
        )
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
}