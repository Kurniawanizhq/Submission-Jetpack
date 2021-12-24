package com.eone.submission1.ui.home.activity

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.eone.submission1.BuildConfig
import com.eone.submission1.ItemListResponse
import com.eone.submission1.R
import com.eone.submission1.ViewModelFactory
import com.eone.submission1.databinding.ActivityHomeBinding
import com.eone.submission1.ui.detail.DetailActivity
import com.eone.submission1.ui.home.HomePagerAdapter
import com.eone.submission1.ui.home.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
private lateinit var array : List<ItemListResponse>
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
                array = it
            })
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        binding.cvPoster.setImageClickListener {
            startActivity(
                Intent(this,DetailActivity::class.java)
                    .putExtra(DetailActivity.EXTRA_ID,array[it].id)
                    .putExtra(DetailActivity.EXTRA_TYPE,"MOVIE")
            )
            println("ARRAY :${array[it].id}")
            Toast.makeText(this, "Clicked item: $it", Toast.LENGTH_SHORT).show()
        }

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
            Glide.with(this)
                .load(BuildConfig.IMAGE_URL+dataMovie[position].backdropPath)
                .error(BuildConfig.IMAGE_URL)
                .into(imageView)
        }
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tvShow)
    }
}