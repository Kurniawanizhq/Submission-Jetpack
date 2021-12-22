package com.eone.submission1.ui.home.activity

import android.graphics.Typeface
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.eone.submission1.*
import com.eone.submission1.databinding.ActivityHomeBinding
import com.eone.submission1.model.DataEntity
import com.eone.submission1.ui.home.HomeViewModel
import com.eone.submission1.ui.home.HomePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.synnapps.carouselview.ImageListener

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory =ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[HomeViewModel::class.java]

        val viewPagerAdapter = HomePagerAdapter(this)
        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            viewModel.getMovies()?.observe(this,{
                bigPoster(it)
                binding.cvPoster.pageCount = it.size
            })
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        binding.viewPager



        val fontTitle : Typeface? = ResourcesCompat.getFont(this,R.font.screamreal)

        binding.collapsingToolbar.apply {
            title = "THE MOVIEDB"
            setCollapsedTitleTypeface(fontTitle)
            setExpandedTitleTypeface(fontTitle)
            elevation = 0f
        }
    }

    private fun bigPoster(dataMovie : List<ItemListResponse>) {
        val result : ArrayList<ItemListResponse> = ArrayList()
        result.addAll(dataMovie)

        binding.cvPoster.setImageListener { position, imageView ->
            println("BUELD : ${BuildConfig.IMAGE_URL+dataMovie[position].backdropPath}")
            Glide.with(this).load(BuildConfig.IMAGE_URL+dataMovie[position].backdropPath).into(imageView)
        }
    }
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tvShow)
    }
}