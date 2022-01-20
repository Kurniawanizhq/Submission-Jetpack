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
import com.eone.submission3.data.local.entity.MovieEntity
import com.eone.submission3.data.local.entity.TvShowEntity
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
//        binding.fabAddToFavorite.setOnClickListener {
//            Toast.makeText(this,"added from favorite", Toast.LENGTH_SHORT).show()
//            FancyToast.makeText(applicationContext,"Added from Favorite",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show()
//        }

        val viewModelFactory = ViewModelFactory.getInstance(this)

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[DetailViewModel::class.java]

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        if (type.equals("MOVIE", ignoreCase = true)) {
            binding.collapsingDetail.title = "Detail Movie"
            viewModel.getMovieDetail(id).observe(this, { resource ->
                when(resource.status){
                    Status.LOADING ->  showProgressBar(true)
                    Status.SUCCES -> {
                        if (resource.data != null){
                            showProgressBar(false)
                            detailMovie(resource.data)
                            statusFavorite(resource.data.isFavorite)
                        }
                        binding.fabAddToFavorite.setOnClickListener {
                            setToFavorite(resource.data)
                        }
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        FancyToast.makeText(this, "Connection Error", Toast.LENGTH_SHORT,FancyToast.ERROR,false).show()
                    }
                }
            })

        } else if (type.equals("TV_SHOW", ignoreCase = true)) {
            binding.collapsingDetail.title = "Detail Tv Show"
            viewModel.getTvShowDetail(id).observe(this, { resource ->
                when(resource.status){
                    Status.LOADING ->  showProgressBar(true)
                    Status.SUCCES -> {
                        if (resource.data != null){
                            showProgressBar(false)

                            detailTvshow(resource.data)
                            statusFavorite(resource.data.isFavorite)

                            binding.fabAddToFavorite.setOnClickListener {
                                setToFavorite(resource.data)
                            }
                        }
                    }
                    Status.ERROR ->{
                        showProgressBar(false)
                        FancyToast.makeText(this, "Connection Error", Toast.LENGTH_SHORT,FancyToast.ERROR,false).show()
                    }
                }
            })
        }

    }

    private fun detailMovie(itemDetail : MovieEntity) {
        binding.apply {

            // Get year
            val date : String = itemDetail.releaseDate
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

            // Get year
            val date : String = itemDetail.releaseDate
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


    private fun statusFavorite(state: Boolean) {
            if (state){
                binding.fabAddToFavorite.setImageResource(R.drawable.ic_favorite_red)
            }
            else{
                binding.fabAddToFavorite.setImageResource(R.drawable.ic_favorite_black)
            }
    }

    private fun setToFavorite(data : Any?) {
        when(data){
            is MovieEntity ->{
                if (data.isFavorite){
                    FancyToast.makeText(this,"${data.title} Removed from Favorite",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show()
                }else{
                    FancyToast.makeText(this,"${data.title} Added from Favorite",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show()
                }
                viewModel.setFavoriteMovie(data, data.isFavorite)
            }
            is TvShowEntity ->{
                if (data.isFavorite){
                    FancyToast.makeText(this,"${data.name} Removed from Favorite",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show()
                }else{
                    FancyToast.makeText(this,"${data.name} Added from Favorite",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show()
                }
                viewModel.setFavoriteTvShow(data,data.isFavorite)
            }
        }
    }

    private fun showProgressBar(state: Boolean) {
        binding.apply {
            if (state) {
                rlDetail.start()
            } else {
                rlDetail.stop()
            }
            appbar.isInvisible = state
            nestedScrollView.isInvisible = state
            fabAddToFavorite.isInvisible = state
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }
}