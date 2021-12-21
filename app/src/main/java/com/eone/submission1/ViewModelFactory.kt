package com.eone.submission1

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eone.submission1.ui.detail.DetailViewModel
import com.eone.submission1.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val apiRepository: ApiRepository) :
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
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(apiRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                HomeViewModel(apiRepository) as T
            }
            else -> {
                throw Throwable("Unknown ViewModel class: " + modelClass.name)
            }
        }
    }
}