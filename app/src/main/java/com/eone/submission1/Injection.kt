package com.eone.submission1

object Injection {
    fun provideRepository() : ContentRepository? {
        val remoteDataSource = RemoteDataSource.getInstance()
        return ContentRepository.getInstance(remoteDataSource)
    }
}