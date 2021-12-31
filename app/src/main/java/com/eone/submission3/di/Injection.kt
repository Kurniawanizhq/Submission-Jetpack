package com.eone.submission3.di

import com.eone.submission3.model.repository.ContentRepository
import com.eone.submission3.model.repository.RemoteDataSource

object Injection {
    fun provideRepository() :ContentRepository? {
        val remoteDataSource = RemoteDataSource.getInstance()
        return ContentRepository.getInstance(remoteDataSource)
    }
}