package com.eone.submission3.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eone.submission3.di.Injection
import com.eone.submission3.model.repository.ContentRepository
import com.eone.submission3.ui.detail.DetailViewModel
import com.eone.submission3.ui.home.HomeViewModel
import com.eone.submission3.ui.home.fragment.favorite.FavoriteViewModel

class ViewModelFactory private constructor(private val contentRepository: ContentRepository) :
    ViewModelProvider.NewInstanceFactory() {

        companion object{
            @Volatile
            private var INSTANCE : ViewModelFactory? = null

            fun getInstance(context: Context) : ViewModelFactory = INSTANCE ?: synchronized(this){
                INSTANCE ?: ViewModelFactory(Injection.provideRepository(context))
            }
        }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(contentRepository) as T

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(contentRepository) as T

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(contentRepository) as T
            else -> {
                throw Throwable("Unknown ViewModel class: " + modelClass.name)
            }
        }
    }
}