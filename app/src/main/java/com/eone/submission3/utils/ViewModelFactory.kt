package com.eone.submission3.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eone.submission3.di.Injection
import com.eone.submission3.model.repository.ContentRepository
import com.eone.submission3.ui.detail.DetailViewModel
import com.eone.submission3.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val contentRepository: ContentRepository?) :
    ViewModelProvider.NewInstanceFactory() {

        companion object{
            @Volatile
            private var INSTANCE : ViewModelFactory? = null

            fun getInstance() : ViewModelFactory = INSTANCE ?: synchronized(this){
                INSTANCE ?: ViewModelFactory(Injection.provideRepository())
            }
        }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(contentRepository) as T

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(contentRepository) as T
            else -> {
                throw Throwable("Unknown ViewModel class: " + modelClass.name)
            }
        }
    }
}