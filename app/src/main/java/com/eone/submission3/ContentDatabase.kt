package com.eone.submission3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eone.submission3.local.MovieEntity
import com.eone.submission3.local.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ContentDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentDao

    companion object {
        @Volatile
        private var INSTANCE: ContentDatabase? = null

        fun getInstance(context: Context): ContentDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext, ContentDatabase::class.java,
                "content.db"
            ).build()
        }
    }
}