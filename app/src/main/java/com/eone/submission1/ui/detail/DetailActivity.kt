package com.eone.submission1.ui.detail

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.eone.submission1.*
import com.eone.submission1.databinding.ActivityDetailBinding
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar?.setNavigationOnClickListener { onBackPressed() }

        showProgressBar(true)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        val viewModelFactory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[DetailViewModel::class.java]

        if (type.equals("MOVIE", ignoreCase = true)) {
            binding.collapsing?.title = "Detail Movie"
            viewModel.getMovieDetail(id)?.observe(this, {
                detailContent(it)
            })
        } else if (type.equals("TV_SHOW", ignoreCase = true)) {
            binding.collapsing?.title = "Detail Tv Show"
            viewModel.getTvShowDetail(id)?.observe(this, {
                detailContent(it)
            })
        }
    }

    private fun detailContent(itemDetail: ItemDetailResponse) {
        showProgressBar(false)

        binding.apply {

            val listGenre = itemDetail.genre.map {
                it.name
            }
            val genreText = replaceList(listGenre.toString())
            val releaseDate = "( ${
                itemDetail.releaseMovieDate?.substring(range = 0..3) ?: itemDetail.releaseTvDate?.substring(
                    range = 0..3
                )
            } )"

            val duration = if (itemDetail.duration == null) {
                replaceList(itemDetail.epsDuration.toString())
            } else {
                replaceList(itemDetail.duration.toString())
            } + " " + resources.getString(R.string.minutes)

            tvTitle.text = itemDetail.title ?: itemDetail.name
            tvReleaseDate?.text = releaseDate
            tvVote?.text = itemDetail.voteAverage.toString()
            tvDuration.text = duration
            tvGenre.text = genreText
            tvDescription.text = itemDetail.overview

            Glide.with(this@DetailActivity)
                .load(BuildConfig.IMAGE_URL + itemDetail.poster_path)
                .error(BuildConfig.IMAGE_URL)
                .into(posterImg)

            Glide.with(this@DetailActivity)
                .asBitmap()
                .load(BuildConfig.IMAGE_URL + itemDetail.backdropPath)
                .error(BuildConfig.IMAGE_URL)
                .into(object : CustomTarget<Bitmap>(){
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        binding.bgImage.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}

                })
        }
    }

    private fun replaceList(text: String): String? {
        return text.replace("[", "").replace("]", "")
    }

    private fun showProgressBar(state: Boolean) {
        if (state) {
            binding.rlDetail?.start()
        } else {
            binding.rlDetail?.stop()
        }
        binding.appbar?.isInvisible = state
        binding.nestedScrollView?.isInvisible = state
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }
}