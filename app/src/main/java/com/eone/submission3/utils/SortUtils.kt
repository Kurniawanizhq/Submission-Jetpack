package com.eone.submission3.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    const val NEWEST = "newest"
    const val OLDEST = "oldest"
    const val POPULARITY = "popularity"

    fun getSortedQueryMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tab_movie ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY release_date DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY release_date ASC")
            }
            POPULARITY -> {
//                simpleQuery.append("SELECT * FROM tab_movie ")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryTvShow(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tab_tvshow ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY release_date DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY release_date ASC")
            }
            POPULARITY -> {
//                simpleQuery.append("ORDER BY popularity DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}