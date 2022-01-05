package com.eone.submission3.ui.detail

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.eone.submission3.*
import com.eone.submission3.databinding.ActivityDetailBinding
import com.eone.submission3.local.MovieEntity
import com.eone.submission3.local.TvShowEntity
import com.eone.submission3.utils.ViewModelFactory
import com.eone.submission3.vo.Status
import com.shashank.sony.fancytoastlib.FancyToast
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel : DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        val fontTitle : Typeface? = ResourcesCompat.getFont(this, R.font.screamreal)
        binding.collapsingDetail.apply {
            setCollapsedTitleTypeface(fontTitle)
            setExpandedTitleTypeface(fontTitle)
            elevation = 0f
        }

        val viewModelFactory = ViewModelFactory.getInstance(this)

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[DetailViewModel::class.java]

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        println("ID BOY $id")

        if (type.equals("MOVIE", ignoreCase = true)) {
            binding.collapsingDetail.title = "Detail Movie"
            viewModel.getMovieDetail(id).observe(this, {
                when(it.status){
                    Status.LOADING ->  showProgressBar(true)
                    Status.SUCCES -> {
                        if (it.data != null){
                            showProgressBar(false)
//                            val state = it.data.isFavorite
                            viewModel.setFavoriteMovie(it.data)
                            detailMovie(it.data)
                        }
                    }
                    Status.ERROR ->{
                        showProgressBar(false)
                        FancyToast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else if (type.equals("TV_SHOW", ignoreCase = true)) {
            binding.collapsingDetail.title = "Detail Tv Show"
            viewModel.getTvShowDetail(id).observe(this, {
                when(it.status){
                    Status.LOADING ->  showProgressBar(true)
                    Status.SUCCES -> {
                        if (it.data != null){
                            showProgressBar(false)
//                            val state = it.data.isFavorite
                            viewModel.setFavoriteTvShow(it.data)
                            detailTvshow(it.data)
                        }
                    }
                    Status.ERROR ->{
                        showProgressBar(false)
                        FancyToast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

    }

    private fun detailMovie(itemDetail : MovieEntity) {

        binding.apply {

            //Get Genre Text
//            val listGenre = itemDetail.genre?.map {
//                it.name
//            }
//            val genreText = replaceList(listGenre.toString())

            // Get year
            val date : String = itemDetail.releaseDate.toString()
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val cal : Calendar = Calendar.getInstance()
            sdf.parse(date)?.let {
                cal.time = it
            }
            val releaseYear = "( ${cal.get(Calendar.YEAR)} )"

            // Get duration from Movies & Tv Show
            val duration = "${itemDetail.duration} ${resources.getString(R.string.minutes)}"

            tvTitle.text = itemDetail.title
            tvReleaseDate.text = releaseYear
            tvVote.text = itemDetail.voteAverage.toString()
            tvDuration.text = duration
            tvGenre.text = itemDetail.genre
            tvDescription.text = itemDetail.overview

            Glide.with(this@DetailActivity)
                .load(BuildConfig.IMAGE_URL + itemDetail.posterPath)
                .placeholder(R.drawable.picture_placeholder)
                .error(BuildConfig.IMAGE_URL)
                .into(posterImg)

            Glide.with(this@DetailActivity)
                .load(BuildConfig.IMAGE_URL + itemDetail.backdropPath)
                .placeholder(R.drawable.picture_placeholder)
                .error(BuildConfig.IMAGE_URL)
                .into(bgImage)

        }
    }

    private fun detailTvshow(itemDetail : TvShowEntity) {

        binding.apply {

            //Get Genre Text
//            val listGenre = itemDetail.genre?.map {
//                it.name
//            }
//            val genreText = replaceList(listGenre.toString())

            // Get year
            val date : String = itemDetail.releaseDate.toString()
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val cal : Calendar = Calendar.getInstance()
            sdf.parse(date)?.let {
                cal.time = it
            }
            val releaseYear = "( ${cal.get(Calendar.YEAR)} )"

            // Get duration from Movies & Tv Show
            val duration = "${itemDetail.duration} ${resources.getString(R.string.minutes)}"

            tvTitle.text = itemDetail.name
            tvReleaseDate.text = releaseYear
            tvVote.text = itemDetail.voteAverage.toString()
            tvDuration.text = duration
            tvGenre.text = itemDetail.genre
            tvDescription.text = itemDetail.overview

            Glide.with(this@DetailActivity)
                .load(BuildConfig.IMAGE_URL + itemDetail.posterPath)
                .placeholder(R.drawable.picture_placeholder)
                .error(BuildConfig.IMAGE_URL)
                .into(posterImg)

            Glide.with(this@DetailActivity)
                .load(BuildConfig.IMAGE_URL + itemDetail.backdropPath)
                .placeholder(R.drawable.picture_placeholder)
                .error(BuildConfig.IMAGE_URL)
                .into(bgImage)

        }
    }

    private fun replaceList(text: String): String {
        return text.replace("[", "").replace("]", "")
    }

    private fun showProgressBar(state: Boolean) {
        if (state) {
            binding.rlDetail.start()
        } else {
            binding.rlDetail.stop()
        }
        binding.appbar.isInvisible = state
        binding.nestedScrollView.isInvisible = state
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }
}