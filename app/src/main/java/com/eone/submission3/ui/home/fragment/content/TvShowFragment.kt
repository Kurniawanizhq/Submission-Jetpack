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
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.PagingData
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
import com.eone.submission3.vo.Status
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.launch

class TvShowFragment : Fragment(), HomeCallback.OnItemClickedTvshow {
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = requireNotNull(_binding)
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (activity != null) {
            val viewModelFactory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(
                this,
                viewModelFactory
            )[HomeViewModel::class.java]
            viewModel.getTvShow().observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.LOADING -> {
                        showProgressBar(true)
                    }
                    Status.SUCCES -> {
                        showProgressBar(false)
                        setLayout(it)
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        FancyToast.makeText(
                            context,
                            "Error Load Data",
                            FancyToast.LENGTH_SHORT,
                            FancyToast.ERROR,
                            false
                        ).show()
                    }
                }


            })
        }
    }

    private fun setLayout(data: Resource<PagingData<TvShowEntity>>) {
        binding.rvTvShow.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = TvShowAdapter(this@TvShowFragment)
        }.also {
            it.adapter.let { adapter ->
                when (adapter) {
                    is TvShowAdapter -> {
                        viewModel.viewModelScope.launch {
                            adapter.submitData(data.data!!)
                        }
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
        if (state) {
            binding.rlTvShow.start()
        } else {
            binding.rlTvShow.stop()
        }
    }

    override fun onItemClickedTvshow(data: TvShowEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_ID, data.tvshowId)
                .putExtra(DetailActivity.EXTRA_TYPE, "TV_SHOW")
        )
    }
}