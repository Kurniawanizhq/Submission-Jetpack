package com.eone.submission1.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eone.submission1.R
import com.eone.submission1.databinding.ActivityHomeBinding
import com.synnapps.carouselview.ImageListener

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.cvPoster.pageCount = bigPoster.size
        binding.cvPoster.setImageListener(imageListener)

        binding.collapsingToolbar.title = "THE MOVIEDB"
    }

    private val imageListener = ImageListener { position, imageView ->
        imageView.setImageResource(bigPoster[position])
    }

    private val bigPoster = intArrayOf(
        R.drawable.latar_spiderman,
        R.drawable.latar_alita,
        R.drawable.latar_arrow,
        R.drawable.latar_gotham,
        R.drawable.latar_hanna,
        R.drawable.latar_naruto_shipudden,
        R.drawable.latar_how_to_train
    )
}