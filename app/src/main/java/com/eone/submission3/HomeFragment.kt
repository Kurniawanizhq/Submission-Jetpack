package com.eone.submission3

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.eone.submission3.data.response.ItemListResponse
import com.eone.submission3.databinding.FragmentHomeBinding
import com.eone.submission3.local.MovieEntity
import com.eone.submission3.local.TvShowEntity
import com.eone.submission3.ui.detail.DetailActivity
import com.eone.submission3.ui.adapter.HomePagerAdapter
import com.eone.submission3.ui.home.HomeViewModel
import com.eone.submission3.utils.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class FragmentHome : Fragment() {

    private lateinit var listMovies: PagedList<MovieEntity>
    private lateinit var listTvshow: PagedList<TvShowEntity>

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
        val viewModelFactory = ViewModelFactory.getInstance(context as Context)
        val viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[HomeViewModel::class.java]

        val viewPagerAdapter = HomePagerAdapter(activity as AppCompatActivity)
        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

//        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                when (position) {
//                    0 -> {
//                        viewModel.getMovies().observe(viewLifecycleOwner, {
//                            it.data?.let { it1 -> bigPosterMovies(it1) }
//                            binding.cvPoster.pageCount = it.data?.size!!
//                            listMovies = it.data
//                        })
//                    }
//                    1 -> {
//                        viewModel.getTvShow().observe(viewLifecycleOwner, {
//                            it.data?.let { it1 -> bigPosterTvshows(it1) }
//                            binding.cvPoster.pageCount = it.data?.size!!
//                            listTvshow = it.data
//                        })
//                    }
//                }
//            }
//        })

//        binding.cvPoster.setImageClickListener {
//            val type = if (listMovies[it]?.title != null) {
//                "MOVIE"
//            } else {
//                "TV_SHOW"
//            }
//            startActivity(
//                Intent(context, DetailActivity::class.java)
//                    .putExtra(DetailActivity.EXTRA_ID, listMovies[it]?.id)
//                    .putExtra(DetailActivity.EXTRA_TYPE, type)
//            )
//        }

        val fontTitle: Typeface? = context?.let { ResourcesCompat.getFont(it, R.font.screamreal) }
        binding.collapsingHome.apply {
            title = "THE MOVIEDB"
            setCollapsedTitleTypeface(fontTitle)
            setExpandedTitleTypeface(fontTitle)
            elevation = 0f
        }
    }


//    private fun bigPosterMovies(dataMovie: PagedList<MovieEntity>) {
//        binding.cvPoster.setImageListener { position, imageView ->
//            Glide.with(this)
//                .load(BuildConfig.IMAGE_URL + dataMovie[position]?.backdropPath)
//                .placeholder(R.drawable.picture_placeholder)
//                .error(BuildConfig.IMAGE_URL)
//                .into(imageView)
//        }
//    }
//
//    private fun bigPosterTvshows(dataMovie: PagedList<TvShowEntity>) {
//        binding.cvPoster.setImageListener { position, imageView ->
//            Glide.with(this)
//                .load(BuildConfig.IMAGE_URL + dataMovie[position]?.backdropPath)
//                .placeholder(R.drawable.picture_placeholder)
//                .error(BuildConfig.IMAGE_URL)
//                .into(imageView)
//        }
//    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tvShow)
    }
}