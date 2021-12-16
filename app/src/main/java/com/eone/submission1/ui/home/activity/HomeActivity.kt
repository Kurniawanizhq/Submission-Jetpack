package com.eone.submission1.ui.home.activity

import android.graphics.Typeface
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.eone.submission1.R
import com.eone.submission1.databinding.ActivityHomeBinding
import com.eone.submission1.model.DataEntity
import com.eone.submission1.ui.home.HomeViewModel
import com.eone.submission1.ui.home.HomePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.synnapps.carouselview.ImageListener

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var slidePoster: ArrayList<DataEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[HomeViewModel::class.java]

        val viewPagerAdapter = HomePagerAdapter(this)
        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        slidePoster = bigPoster(viewModel.getMovies(),
            viewModel.getTvShow())

        binding.cvPoster.pageCount = slidePoster.size
        binding.cvPoster.setImageListener(imageListener)

        val fontTitle : Typeface? = ResourcesCompat.getFont(this,R.font.screamreal)

        binding.collapsingToolbar.apply {
            title = "THE MOVIEDB"
            setCollapsedTitleTypeface(fontTitle)
            setExpandedTitleTypeface(fontTitle)
            elevation = 0f
        }
    }

    private val imageListener = ImageListener { position, imageView ->
        imageView.setImageResource(slidePoster[position].background)
    }

    private fun bigPoster(dataMovie : ArrayList<DataEntity>,dataTvShow : ArrayList<DataEntity>) : ArrayList<DataEntity>{
        val result : ArrayList<DataEntity> = ArrayList()
        result.addAll(dataMovie)
        result.addAll(dataTvShow)
        return result
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tvShow)
    }
}