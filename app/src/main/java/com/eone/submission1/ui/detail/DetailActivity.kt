package com.eone.submission1.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.eone.submission1.databinding.ActivityDetailBinding
import com.eone.submission1.model.DataEntity

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private lateinit var result : DataEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Film"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val id = intent.getStringExtra(EXTRA_ID)
        val type = intent.getStringExtra(EXTRA_TYPE)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        if (id != null) {
            if (type.equals("MOVIE", ignoreCase = true)) {
                viewModel.setMoviesId(id)
                result = viewModel.getDetailMovieById()
            } else if (type.equals("TV_SHOW", ignoreCase = true)) {
                viewModel.setTvShowId(id)
                result = viewModel.getDetailTvShowById()
            }
            detailContent(result)
        }
    }

    private fun detailContent(dataEntity: DataEntity){

        binding.apply {

            tvTitle.text = dataEntity.title
            tvGenre.text = dataEntity.genre
            tvDuration.text = dataEntity.duration
            tvDescription.text = dataEntity.overview

            Glide.with(this@DetailActivity)
                .clear(posterImg)
            Glide.with(this@DetailActivity)
                .clear(bgImage)

            Glide.with(this@DetailActivity)
                .load(dataEntity.poster)
                .into(posterImg)
            Glide.with(this@DetailActivity)
                .load(dataEntity.background)
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