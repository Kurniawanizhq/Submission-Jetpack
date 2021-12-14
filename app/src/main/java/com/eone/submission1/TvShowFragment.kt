package com.eone.submission1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.eone.submission1.data.Callback
import com.eone.submission1.databinding.FragmentTvShowBinding
import com.eone.submission1.model.DataEntity
import com.eone.submission1.ui.detail.DetailActivity

class TvShowFragment : Fragment(), Callback {
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
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieViewModel::class.java]
            val movies = viewModel.getTvShow()

            setLayout(movies)
        }
    }

    private fun setLayout(data: List<DataEntity>) {
        binding.rvTvShow.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = MovieAdapter(this@TvShowFragment)
        }.also {
            it.adapter.let { adapter ->
                when (adapter) {
                    is MovieAdapter -> {
                        adapter.setMovies(data)
                    }
                }

            }
        }
    }

    override fun onItemClicked(data: DataEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_ID,data.id)
                .putExtra(DetailActivity.EXTRA_TYPE,"TV_SHOW")
        )
    }
}