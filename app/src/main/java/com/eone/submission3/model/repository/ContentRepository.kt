package com.eone.submission3.model.repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.eone.submission3.ApiResponse
import com.eone.submission3.AppExecutors
import com.eone.submission3.NetworkBoundResource
import com.eone.submission3.data.response.ItemDetailResponse
import com.eone.submission3.data.response.ItemListResponse
import com.eone.submission3.local.LocalDataSource
import com.eone.submission3.local.MovieEntity
import com.eone.submission3.local.TvShowEntity
import com.eone.submission3.model.source.ContentDataSource
import com.eone.submission3.vo.Resource
import kotlinx.coroutines.flow.Flow

class ContentRepository(
    private val remoteRepository: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ContentDataSource {

    companion object {
        @Volatile
        private var INSTANCE: ContentRepository? = null

        fun getInstance(
            remoteRepository: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): ContentRepository = INSTANCE ?: synchronized(this){
            INSTANCE ?: ContentRepository(remoteRepository,localDataSource,appExecutors)
        }
    }

    override fun getMovie(): LiveData<Resource<PagingData<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagingData<MovieEntity>, List<ItemListResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagingData<MovieEntity>> {
//                val config = PagedList.Config.Builder()
//                    .setEnablePlaceholders(false)
//                    .setInitialLoadSizeHint(4)
//                    .setPageSize(4)
//                    .build()
//                println("NGAMBIL DATABASE")

//                return LivePagedListBuilder(localDataSource.getMovies(), config).build()

                 val movieConfig = PagingConfig(pageSize = 5)
                val moviePagingFactory = {
                    localDataSource.getMovies()
                }
                print("DATA LOKAL ${Pager(config = movieConfig, pagingSourceFactory = moviePagingFactory).flow}")

                return Pager(config = movieConfig, pagingSourceFactory = moviePagingFactory).liveData
            }

            override fun shouldFetch(data: PagingData<MovieEntity>?): Boolean = data != null


            override fun createCall(): LiveData<ApiResponse<List<ItemListResponse>>> =
                remoteRepository.getMovie()


            override fun saveCallResult(data: List<ItemListResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (item in data) {
                    val movie = MovieEntity(
                        movieId = item.id,
                        backdropPath = item.backdropPath ?: "",
                        genre = "",
                        overview = item.overview ?: "",
                        posterPath = item.posterPath ?: "",
                        title = item.title ?:"",
                        duration = "",
                        releaseDate = "",
                        voteAverage = 0.0,
                        isFavorite = false
                    )
                    movieList.add(movie)
            }
//                println("insert movie $movieList")
                localDataSource.insertMovies(movieList)
            }


        }.asLiveData()
    }


    override fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, ItemDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getDetailMovie(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean = data != null && data.duration == "" && data.voteAverage == 0.0

            override fun createCall(): LiveData<ApiResponse<ItemDetailResponse>> = remoteRepository.getMovieDetail(movieId)

            override fun saveCallResult(data: ItemDetailResponse) {
                val genre = StringBuilder().append("")

                for (i in data.genre.indices) {
                    if (i < data.genre.size - 1) {
                        genre.append("${data.genre[i].name}, ")
                    } else {
                        genre.append(data.genre[i].name)
                    }
                }

                val movie = MovieEntity(
                    movieId = data.id,
                    backdropPath = data.backdropPath,
                    genre = genre.toString(),
                    overview = data.overview ?: "",
                    posterPath = data.posterPath,
                    title = data.title ?:"",
                    duration = data.duration.toString(),
                    releaseDate = data.releaseMovieDate ?:"",
                    voteAverage = data.voteAverage,
                    isFavorite = false
                )
                println("RESULT : $movie")
                localDataSource.setFavoriteMovie(movie, false)
            }

        }.asLiveData()

    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie, state)
        }
    }

    override fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<ItemListResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean = data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ItemListResponse>>> =
                remoteRepository.getTvShow()

            override fun saveCallResult(data: List<ItemListResponse>) {
                val tvList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvshow = TvShowEntity(
                        id = null,
                        tvshowId = response.id,
                        backdropPath = response.backdropPath,
                        genre = "",
                        name = response.name,
                        duration = "",
                        overview = response.overview,
                        posterPath = response.posterPath,
                        releaseDate = "",
                        voteAverage = 0.0,
                        isFavorite = false
                    )
                    tvList.add(tvshow)
                }
                localDataSource.insertTvShow(tvList)
            }

        }.asLiveData()
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, ItemDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getDetailTvShow(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean = data != null && data.duration == "" && data.voteAverage == 0.0

            override fun createCall(): LiveData<ApiResponse<ItemDetailResponse>> =
                remoteRepository.getTvShowDetail(tvShowId)

            override fun saveCallResult(data: ItemDetailResponse) {
                val genre = StringBuilder().append("")

                for (i in data.genre.indices) {
                    if (i < data.genre.size - 1) {
                        genre.append("${data.genre[i].name}, ")
                    } else {
                        genre.append(data.genre[i].name)
                    }
                }

                val tvshow = TvShowEntity(
                    id = null,
                    tvshowId = data.id,
                    backdropPath = data.backdropPath,
                    genre = genre.toString(),
                    overview = data.overview,
                    posterPath = data.posterPath,
                    name = data.title,
                    duration = data.releaseTvDate,
                    releaseDate = data.releaseTvDate,
                    voteAverage = data.voteAverage,
                    isFavorite = false
                )
                println("data tv : $tvshow")
                localDataSource.setFavoriteTvShow(tvshow, false)
            }

        }.asLiveData()
    }


    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShow, state)
        }
    }
}
