package com.eone.submission1.ui.detail

import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.eone.submission1.*
import com.eone.submission1.databinding.ActivityDetailBinding
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar?.setNavigationOnClickListener { onBackPressed() }
        val fontTitle : Typeface? = ResourcesCompat.getFont(this,R.font.screamreal)
        binding.collapsingDetail?.apply {
            setCollapsedTitleTypeface(fontTitle)
            setExpandedTitleTypeface(fontTitle)
            elevation = 0f
        }

        showProgressBar(true)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        val viewModelFactory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[DetailViewModel::class.java]

        if (type.equals("MOVIE", ignoreCase = true)) {
            binding.collapsingDetail?.title = "Detail Movie"
            viewModel.getMovieDetail(id)?.observe(this, {
                detailContent(it)
            })
        } else if (type.equals("TV_SHOW", ignoreCase = true)) {
            binding.collapsingDetail?.title = "Detail Tv Show"
            viewModel.getTvShowDetail(id)?.observe(this, {
                detailContent(it)
            })
        }
    }

    private fun detailContent(itemDetail: ItemDetailResponse) {
        showProgressBar(false)

        binding.apply {

            //Get Genre Text
            val listGenre = itemDetail.genre.map {
                it.name
            }
            val genreText = replaceList(listGenre.toString())

            // Get year
            val date : String = itemDetail.releaseMovieDate ?: itemDetail.releaseTvDate.toString()
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val cal : Calendar = Calendar.getInstance()
            cal.time = sdf.parse(date)
            val releaseYear = "( ${cal.get(Calendar.YEAR)} )"

            // Get duration from Movies & Tv Show
            val duration = if (itemDetail.duration == null) {
                replaceList(itemDetail.epsDuration.toString())
            } else {
                replaceList(itemDetail.duration.toString())
            } + " " + resources.getString(R.string.minutes)

            tvTitle.text = itemDetail.title ?: itemDetail.name
            tvReleaseDate?.text = releaseYear
            tvVote?.text = itemDetail.voteAverage.toString()
            tvDuration.text = duration
            tvGenre.text = genreText
            tvDescription.text = itemDetail.overview

            Glide.with(this@DetailActivity)
                .load(BuildConfig.IMAGE_URL + itemDetail.posterPath)
                .error(BuildConfig.IMAGE_URL)
                .into(posterImg)

            Glide.with(this@DetailActivity)
                .load(BuildConfig.IMAGE_URL + itemDetail.backdropPath)
                .error(BuildConfig.IMAGE_URL)
                .into(bgImage)
            binding.bgImage.tag = itemDetail.backdropPath

        }
    }

    private fun replaceList(text: String): String {
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