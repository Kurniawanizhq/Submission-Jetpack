package com.eone.submission1

import android.content.Context

object Injection {
    fun provideRepository() : ApiRepository{
        val remoteDataSource = RemoteDataSource.getInstance()
        return ApiRepository.getInstance(remoteDataSource)!!
    }
}