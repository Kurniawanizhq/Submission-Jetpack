package com.eone.submission3.di

import android.content.Context
import com.eone.submission3.utils.AppExecutors
import com.eone.submission3.data.local.room.ContentDatabase
import com.eone.submission3.data.local.LocalDataSource
import com.eone.submission3.model.repository.ContentRepository
import com.eone.submission3.model.repository.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): ContentRepository {
        val database = ContentDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.contentDao())
        val appExecutors = AppExecutors()
        return ContentRepository.getInstance(remoteDataSource,localDataSource,appExecutors)
    }
}