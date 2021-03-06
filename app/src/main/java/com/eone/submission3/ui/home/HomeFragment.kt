package com.eone.submission3.ui.home

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.eone.submission3.BuildConfig
import com.eone.submission3.R
import com.eone.submission3.databinding.FragmentHomeBinding
import com.eone.submission3.data.local.entity.MovieEntity
import com.eone.submission3.data.local.entity.TvShowEntity
import com.eone.submission3.ui.adapter.HomePagerAdapter
import com.eone.submission3.ui.detail.DetailActivity
import com.eone.submission3.utils.SortUtils
import com.eone.submission3.utils.ViewModelFactory
import com.eone.submission3.vo.Status
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var listMovies: List<MovieEntity>
    private lateinit var listTvshow: List<TvShowEntity>

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        val viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[HomeViewModel::class.java]


        val viewPagerAdapter = HomePagerAdapter(activity as AppCompatActivity)
        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        viewModel.getMovies(SortUtils.POPULARITY).observe(viewLifecycleOwner, {
                            if (it.data != null) {
                                when (it.status) {
                                    Status.LOADING -> {
                                        showProgressBar(true)
                                    }
                                    Status.SUCCES -> {
                                        showProgressBar(false)
                                        bigPosterMovies(it.data)
                                        binding.cvPoster.pageCount = it.data.size
                                        listMovies = it.data
                                    }
                                    Status.ERROR -> {
                                        showProgressBar(false)
                                    }
                                }

                            }
                        })
                    }
                    1 -> {
                        viewModel.getTvShow(SortUtils.POPULARITY).observe(viewLifecycleOwner, {
                            if (it.data != null) {
                                when (it.status) {
                                    Status.LOADING -> {showProgressBar(true)}
                                    Status.SUCCES -> {
                                        showProgressBar(false)
                                        bigPosterTvshows(it.data)
                                        binding.cvPoster.pageCount = it.data.size
                                        listTvshow = it.data
                                    }
                                    Status.ERROR -> {
                                        showProgressBar(false)
                                    }
                                }
                            }
                        })

                    }
                }
            }
        })

        binding.cvPoster.setImageClickListener {
            val type = if (listMovies[it].title.isNotEmpty()) {
                "MOVIE"
            } else {
                "TV_SHOW"
            }
            startActivity(
                Intent(context, DetailActivity::class.java)
                    .putExtra(DetailActivity.EXTRA_ID, listMovies[it].movieId)
                    .putExtra(DetailActivity.EXTRA_TYPE, type)
            )
        }

        val fontTitle: Typeface? = context?.let { ResourcesCompat.getFont(it, R.font.screamreal) }
        binding.collapsingHome.apply {
            title = "THE MOVIEDB"
            setCollapsedTitleTypeface(fontTitle)
            setExpandedTitleTypeface(fontTitle)
            elevation = 0f
        }
    }


    private fun bigPosterMovies(dataMovie: List<MovieEntity>) {
        binding.cvPoster.setImageListener { position, imageView ->
            Glide.with(this)
                .load(BuildConfig.IMAGE_URL + dataMovie[position].backdropPath)
                .placeholder(R.drawable.picture_placeholder)
                .error(BuildConfig.IMAGE_URL)
                .into(imageView)
        }
    }

    private fun bigPosterTvshows(dataMovie: List<TvShowEntity>) {
        binding.cvPoster.setImageListener { position, imageView ->
            Glide.with(this)
                .load(BuildConfig.IMAGE_URL + dataMovie[position].backdropPath)
                .placeholder(R.drawable.picture_placeholder)
                .error(BuildConfig.IMAGE_URL)
                .into(imageView)
        }
    }

    private fun showProgressBar(state: Boolean) {
        binding.ivCarousel.isInvisible = state
        if (state) {
            binding.rlHome.start()
        } else {
            binding.rlHome.stop()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tvShow)
    }
}