package com.eone.submission2.di

import com.eone.submission2.model.repository.ContentRepository
import com.eone.submission2.model.repository.RemoteDataSource

object Injection {
    fun provideRepository() : ContentRepository? {
        val remoteDataSource = RemoteDataSource.getInstance()
        return ContentRepository.getInstance(remoteDataSource)
    }
}