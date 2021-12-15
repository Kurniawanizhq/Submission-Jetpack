package com.eone.submission1.ui.home

import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.eone.submission1.R
import com.eone.submission1.data.DataDummy
import com.eone.submission1.databinding.ActivityHomeBinding
import com.eone.submission1.model.DataEntity
import com.synnapps.carouselview.ImageListener

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var list: ArrayList<DataEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[HomeViewModel::class.java]

        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        list = bigPoster(viewModel.getMovies(),
            viewModel.getTvShow())

        binding.cvPoster.pageCount = list.size
        binding.cvPoster.setImageListener(imageListener)

        binding.collapsingToolbar.apply {
            title = "THE MOVIEDB"
            setCollapsedTitleTypeface(Typeface.SANS_SERIF)
        }
    }

    private val imageListener = ImageListener { position, imageView ->
        imageView.setImageResource(list[position].background)
    }

    private fun bigPoster(dataMovie : ArrayList<DataEntity>,dataTvShow : ArrayList<DataEntity>) : ArrayList<DataEntity>{
        val result : ArrayList<DataEntity> = ArrayList()
        result.addAll(dataMovie)
        result.addAll(dataTvShow)
        return result
    }
}