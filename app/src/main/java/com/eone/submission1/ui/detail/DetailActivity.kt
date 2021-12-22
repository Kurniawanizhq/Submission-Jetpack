package com.eone.submission1.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.eone.submission1.*
import com.eone.submission1.data.DataDummy
import com.eone.submission1.databinding.ActivityDetailBinding
import com.eone.submission1.model.DataEntity

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
//    private lateinit var result : ItemDetailResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Detail Film"
            setDisplayHomeAsUpEnabled(true)
        }

        val id = intent.getIntExtra(EXTRA_ID,0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        val viewModelFactory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[DetailViewModel::class.java]

        if (type.equals("MOVIE", ignoreCase = true)) {
            println("pieleh")
            viewModel.getMovieDetail(id)?.observe(this,{
                println("eat : $it")
                detailContent(it)
            })
        } else if (type.equals("TV_SHOW", ignoreCase = true)) {
            viewModel.getTvShowDetail(id)?.observe(this,{
                detailContent(it)
            })
        }
    }

    private fun detailContent(itemDetail: ItemDetailResponse){
        binding.apply {

            tvTitle.text = itemDetail.title
            tvGenre.text = itemDetail.genre.toString()
            tvDuration.text = itemDetail.duration.toString() ?: itemDetail.epsDuration.toString()
            tvDescription.text = itemDetail.overview

            Glide.with(this@DetailActivity)
                .clear(posterImg)
            Glide.with(this@DetailActivity)
                .clear(bgImage)

            Glide.with(this@DetailActivity)
                .load(BuildConfig.IMAGE_URL+itemDetail.poster_path)
                .into(posterImg)
            Glide.with(this@DetailActivity)
                .load(BuildConfig.IMAGE_URL+itemDetail.backdropPath)
                .into(bgImage)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }
}